/** 
 * Project Name:eve-server 
 * File Name:DatasourceComponent.java 
 * Package Name:com.s3s3l.eve.component 
 * Date:Oct 9, 20171:36:40 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.component;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.s3s3l.eve.configuration.JDBCConfiguration;
import com.s3s3l.jdbc.exec.DefaultSqlExecutor;
import com.s3s3l.jdbc.exec.SqlExecutor;

/**
 * <p>
 * </p> 
 * ClassName:DatasourceComponent <br> 
 * Date:     Oct 9, 2017 1:36:40 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Component
public class DatasourceComponent {


    @Bean
    public javax.sql.DataSource dataSource(JDBCConfiguration jdbcConfiguration) throws Exception {
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(jdbcConfiguration);
        return datasource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(javax.sql.DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    @Bean
    public SqlExecutor sqlExecutor(javax.sql.DataSource datasource) {
        SqlExecutor exec = new DefaultSqlExecutor();
        exec.setDataSource(datasource);
        return exec;
    }
}
  