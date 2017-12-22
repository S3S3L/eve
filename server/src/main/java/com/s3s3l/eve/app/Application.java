/** 
 * Project Name:eve-server 
 * File Name:Application.java 
 * Package Name:com.s3s3l.eve.app 
 * Date:Aug 30, 20175:04:33 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.app;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.s3s3l.eve.annotation.Primary;
import com.s3s3l.eve.configuration.DatasourceConfiguration;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.jdbc.bind.annotation.Column;
import com.s3s3l.jdbc.bind.annotation.SqlModel;
import com.s3s3l.utils.file.FileUtil;
import com.s3s3l.utils.reflect.ReflectionUtils;
import com.s3s3l.utils.reflect.ReflectionsHelper;
import com.s3s3l.utils.string.StringUtil;

/**
 * <p>
 * </p>
 * ClassName:Application <br>
 * Date: Aug 30, 2017 5:04:33 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@EnableAutoConfiguration
@ComponentScan({ "com.s3s3l.eve" })
@SpringBootApplication
public class Application extends ContextIdApplicationContextInitializer {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static ConfigurableApplicationContext ctx;

    private static final String[] LOG4J_CONFIG = new String[] { "file:config/log4j/log4j2.yml",
            "file:conf/log4j/log4j2.yml", "file:log4j2.yml", "classpath:config/log4j/log4j2.yml",
            "classpath:conf/log4j/log4j2.yml", "classpath:log4j2.yml" };

    public static void main(String[] args) throws Throwable {
        configureLog4j();
        ctx = SpringApplication.run(Application.class, args);

        DatasourceConfiguration datasourceConfiguration = ctx.getBean(DatasourceConfiguration.class);

        initHSQLDB(datasourceConfiguration.isRebuildDatabase());

        CommonService commonService = ctx.getBean(CommonService.class);
        commonService.loadItems();
        commonService.loadBluePrints();

        logger.info("Started.");
    }

    /**
     *
     * 配置log4j2
     *
     * @since JDK 1.8
     */
    private static void configureLog4j() {
        Properties props = System.getProperties();
        props.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        File configFile = FileUtil.getFirstExistFile(LOG4J_CONFIG);
        if (configFile != null) {
            props.setProperty("log4j.configurationFile", configFile.getAbsolutePath());
        }
    }

    private static void initHSQLDB(boolean rebuildTable) throws SQLException {
        logger.info("Starting to init HSQLDB...");
        DataSource datasource = ctx.getBean(DataSource.class);
        Set<Class<?>> tableBeans = ReflectionsHelper.getTypesAnnotatedWith(SqlModel.class, true,
                "com.s3s3l.eve.model.eve");
        logger.info("{} tables was found.", tableBeans.size());
        try (Connection conn = datasource.getConnection()) {
            for (Class<?> tableBean : tableBeans) {
                SqlModel sqlModel = ReflectionUtils.getAnnotation(tableBean, SqlModel.class);
                logger.info("table found '{}'", sqlModel.table());
                StringBuilder insertSql = new StringBuilder("\nINSERT INTO\n\t");
                insertSql.append(sqlModel.table())
                        .append("\n(\n\t");

                List<String> columnNames = new ArrayList<>();
                List<String> columns = new ArrayList<>();
                for (Field columnField : ReflectionUtils.getFields(tableBean)) {
                    if (!columnField.isAnnotationPresent(Column.class)) {
                        continue;
                    }
                    Column column = columnField.getAnnotation(Column.class);
                    String name = StringUtil.isEmpty(column.name()) ? columnField.getName() : column.name();
                    columnNames.add(name);
                    if (columnField.isAnnotationPresent(Primary.class)) {
                        columns.add(String.join(" ", name, column.dbType(), "primary key"));
                    } else {
                        columns.add(String.join(" ", name, column.dbType()));
                    }
                }

                insertSql.append(String.join(",\n\t", columnNames))
                        .append("\n)\nVALUES\n(\n\t")
                        .append(String.join(",\n\t", columnNames.stream()
                                .map(r -> String.format("#{info.%s}", r))
                                .collect(Collectors.toList())))
                        .append("\n);");

                String sql = String.format("create table if not exists %s(%s);", sqlModel.table(),
                        String.join(",", columns));
                try (Statement statement = conn.createStatement()) {
                    if (rebuildTable) {
                        String dropSql = "drop table if exists ".concat(sqlModel.table());
                        logger.info("Excutiong sql '{}'", dropSql);
                        statement.executeUpdate(dropSql);
                    }
                    logger.info("Excutiong sql '{}'", sql);
                    statement.executeUpdate(sql);
                }
            }
        }
        logger.info("Finished to init HSQLDB.");
    }
}
