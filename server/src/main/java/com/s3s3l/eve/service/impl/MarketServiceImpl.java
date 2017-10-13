/** 
 * Project Name:eve-server 
 * File Name:MarketServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Sep 29, 20173:24:19 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.eve.configuration.DatasourceConfiguration;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.handler.GlobalizationHelper;
import com.s3s3l.eve.handler.PaginRequestHelper;
import com.s3s3l.eve.model.enumetrations.esi.EnumOrderType;
import com.s3s3l.eve.model.enumetrations.eve.EnumCacheScope;
import com.s3s3l.eve.model.enumetrations.eve.EnumGlobalizationType;
import com.s3s3l.eve.model.eve.GlobalizationTable;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Material;
import com.s3s3l.eve.model.eve.items.Type;
import com.s3s3l.eve.model.eve.market.Order;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.eve.service.MarketService;
import com.s3s3l.http.HttpUtil;
import com.s3s3l.jdbc.exec.SqlExecutor;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.resource.JsonHelper;
import com.s3s3l.utils.collection.MapBuilder;
import com.s3s3l.utils.concurrent.TaskExecutor;
import com.s3s3l.utils.file.FileUtil;
import com.s3s3l.utils.verify.Verify;

/**
 * <p>
 * </p>
 * ClassName:MarketServiceImpl <br>
 * Date: Sep 29, 2017 3:24:19 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class MarketServiceImpl implements MarketService {
    private final JacksonHelper json = JacksonUtil.defaultHelper;
    private final JsonHelper yml = JacksonUtil.create(new YAMLFactory())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalizationHelper globalizationHelper;
    @Autowired
    private PaginRequestHelper paginRequestHelper;
    @Autowired
    private HttpUtil http;
    @Autowired
    private ESIConfiguration esiConfiguration;
    @Autowired
    private DatasourceConfiguration datasourceConfiguration;
    @Autowired
    private SqlExecutor sqlExecutor;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private CacheHelper<String, Object> cache;
    @Autowired
    private CommonService commonService;

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
                    .toObject(FileUtil.getFirstExistFile("file:eve_data/fsd/blueprints.yaml"),
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

                Type type = commonService.getTypeDetail(blueprintTypeID);
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
    public BigDecimal getCost(String regionID, Blueprint blueprint) {
        List<Material> materials = blueprint.getActivities()
                .getManufacturing()
                .getMaterials();

        BigDecimal cost = BigDecimal.ZERO;

        for (Material material : materials) {
            Order order = getOrders(regionID, material.getTypeID(), EnumOrderType.sell).stream()
                    .filter(r -> r.getVolumeRemain() > material.getQuantity())
                    .sorted((r, s) -> r.getPrice()
                            .compareTo(r.getPrice()))
                    .findFirst()
                    .orElse(null);
            if (order == null) {
                return BigDecimal.ZERO;
            }

            cost.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(material.getQuantity())));
        }
        return cost;
    }

    @Override
    public List<Order> getOrders(String regionID, String typeID, EnumOrderType orderType) {
        return paginRequestHelper.queryAll((page) -> {
            return http.doGet(new MapBuilder<String, String>(new HashMap<>()).put("region_id", regionID)
                    .build(),
                    esiConfiguration.getInterfaces()
                            .getOrders()
                            .getUrl(esiConfiguration.getEndpoint()),
                    new MapBuilder<String, Object>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("page", String.valueOf(page))
                            .put("order_type", Optional.ofNullable(orderType)
                                    .map(r -> r.name())
                                    .orElse(null))
                            .put("type_id", typeID)
                            .build(),
                    null);
        }, Order.class);
    }

}
