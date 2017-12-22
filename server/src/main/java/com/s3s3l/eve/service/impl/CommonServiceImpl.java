/** 
 * Project Name:eve-server 
 * File Name:CommonServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Oct 13, 20176:47:32 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.eve.configuration.DatasourceConfiguration;
import com.s3s3l.eve.handler.globalization.GlobalizationHelper;
import com.s3s3l.eve.model.enumetrations.eve.EnumCacheScope;
import com.s3s3l.eve.model.enumetrations.eve.EnumGlobalizationType;
import com.s3s3l.eve.model.eve.GlobalizationTable;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Type;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.jdbc.exec.SqlExecutor;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.resource.JsonHelper;
import com.s3s3l.utils.concurrent.TaskExecutor;
import com.s3s3l.utils.file.FileUtil;
import com.s3s3l.utils.verify.Verify;

/**
 * <p>
 * </p>
 * ClassName:CommonServiceImpl <br>
 * Date: Oct 13, 2017 6:47:32 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class CommonServiceImpl implements CommonService {
    private final JacksonHelper json = JacksonUtil.defaultHelper;
    private final JsonHelper yml = JacksonUtil.create(new YAMLFactory())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalizationHelper globalizationHelper;
    @Autowired
    private DatasourceConfiguration datasourceConfiguration;
    @Autowired
    private SqlExecutor sqlExecutor;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private CacheHelper<String, Object> cache;

    @Override
    public void loadItems() {
        logger.info("Starting to load items...");
        Map<Integer, Type> typeMap = null;
        try {
            typeMap = yml.toObject(FileUtil.getFirstExistFile(datasourceConfiguration.getType()),
                    new TypeReference<Map<Integer, Type>>() {
                    });
        } catch (IOException e) {
            logger.error("Fail to load items.", e);
        }

        if (typeMap == null || typeMap.isEmpty()) {
            logger.info("No item was found. Skip.");
            return;
        }

        for (Entry<Integer, Type> entry : typeMap.entrySet()) {
            String typeID = String.valueOf(entry.getKey());
            Type type = entry.getValue();
            type.setTypeID(typeID);
            cache.put(typeID, type, EnumCacheScope.Type.name());

            taskExecutor.execute(() -> {
                if (sqlExecutor.select(type, Type.class)
                        .isEmpty()) {
                    logger.info("Loading type {} {} {}.", typeID, type.getName()
                            .getEn(),
                            type.getName()
                                    .getZh());
                    type.setTraitsJson(json.toJsonString(type.getTraits()));
                    type.setMasteriesJson(json.toJsonString(type.getMasteries()));
                    sqlExecutor.insert(Arrays.asList(type), Type.class);
                    sqlExecutor.insert(globalizationHelper.buildGlobalizationTables(type, Type.class, type.getTypeID(),
                            EnumGlobalizationType.Type), GlobalizationTable.class);
                }
            });
        }
    }

    @Override
    public void loadBluePrints() {
        logger.info("Starting to load blueprints...");
        Map<Integer, Blueprint> blueprintMap = null;
        try {
            blueprintMap = JacksonUtil.create(new YAMLFactory())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .toObject(FileUtil.getFirstExistFile(datasourceConfiguration.getBlueprint()),
                            new TypeReference<Map<Integer, Blueprint>>() {
                            });
        } catch (IOException e) {
            logger.error("Fail to load blueprints.", e);
        }

        if (blueprintMap == null || blueprintMap.isEmpty()) {
            logger.info("No blueprint was found. Skip.");
            return;
        }

        for (Entry<Integer, Blueprint> entry : blueprintMap.entrySet()) {
            taskExecutor.execute(() -> {
                String blueprintTypeID = String.valueOf(entry.getKey());
                Blueprint blueprint = entry.getValue();
                blueprint.setBlueprintTypeID(blueprintTypeID);

                Type type = getTypeDetail(blueprintTypeID);
                Verify.notNull(type);
                blueprint.setName(type.getName()
                        .getEn());
                blueprint.setgName(type.getName());

                cache.put(blueprintTypeID, blueprint, EnumCacheScope.Blueprint.name());

                if (sqlExecutor.select(blueprint, Blueprint.class)
                        .isEmpty()) {
                    logger.info("Loading type {} {} {}.", blueprintTypeID, blueprint.getgName()
                            .getEn(),
                            blueprint.getgName()
                                    .getZh());
                    sqlExecutor.insert(Arrays.asList(blueprint), Blueprint.class);
                    sqlExecutor.insert(globalizationHelper.buildGlobalizationTables(blueprint, Blueprint.class,
                            blueprintTypeID, EnumGlobalizationType.Blueprint), GlobalizationTable.class);
                }
            });
        }
    }

    @Override
    public List<Type> getType(Type type) {
        return sqlExecutor.select(type, Type.class);
    }

    @Override
    public Type getTypeDetail(String typeID) {
        return (Type) cache.get(typeID, EnumCacheScope.Type.name());
    }

    @Override
    public List<Blueprint> getBlueprint(Blueprint blueprint) {
        return sqlExecutor.select(blueprint, Blueprint.class);
    }

    @Override
    public Blueprint getBlueprintDetail(String blueprintTypeID) {
        return (Blueprint) cache.get(blueprintTypeID, EnumCacheScope.Blueprint.name());
    }

}
