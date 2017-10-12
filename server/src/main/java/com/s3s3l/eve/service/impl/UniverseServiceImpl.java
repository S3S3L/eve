/** 
 * Project Name:eve-server 
 * File Name:UniverseServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Sep 29, 20177:01:40 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.io.PatternFilenameFilter;
import com.s3s3l.eve.configuration.DatasourceConfiguration;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.handler.GlobalizationHelper;
import com.s3s3l.eve.model.enumetrations.eve.EnumGlobalizationType;
import com.s3s3l.eve.model.eve.GlobalizationTable;
import com.s3s3l.eve.model.eve.esiresponse.MoonResponse;
import com.s3s3l.eve.model.eve.esiresponse.PlanetResponse;
import com.s3s3l.eve.model.eve.esiresponse.SolarSystemResponse;
import com.s3s3l.eve.model.eve.esiresponse.StarResponse;
import com.s3s3l.eve.model.eve.universe.Moon;
import com.s3s3l.eve.model.eve.universe.Planet;
import com.s3s3l.eve.model.eve.universe.Region;
import com.s3s3l.eve.model.eve.universe.SolarSystem;
import com.s3s3l.eve.model.eve.universe.Star;
import com.s3s3l.eve.model.eve.universe.base.Constellation;
import com.s3s3l.eve.service.UniverseService;
import com.s3s3l.http.HttpUtil;
import com.s3s3l.jdbc.exec.SqlExecutor;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.resource.JsonHelper;
import com.s3s3l.utils.collection.MapBuilder;
import com.s3s3l.utils.concurrent.TaskExecutor;
import com.s3s3l.utils.file.FileUtil;

/**
 * <p>
 * </p>
 * ClassName:UniverseServiceImpl <br>
 * Date: Sep 29, 2017 7:01:40 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@SuppressWarnings("unused")
public class UniverseServiceImpl implements UniverseService {
    private final JacksonHelper json = JacksonUtil.defaultHelper;
    private final JsonHelper yml = JacksonUtil.create(new YAMLFactory())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalizationHelper globalizationHelper;
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

    @Override
    public void loadUniverse() {
        File eve = FileUtil.getFirstExistFile(datasourceConfiguration.getUniverse()
                .getEve());
        for (File region : eve.listFiles()) {
            taskExecutor.execute(() -> {
                try {
                    Region regionInfo = yml.toObject(
                            region.listFiles(new PatternFilenameFilter("region.staticdata"))[0], Region.class);

                    Region regionDetail = getRegion(regionInfo.getRegionID());

                    yml.update(regionInfo, regionDetail, Region.class);

                    logger.info("Feaching region {} {} {}.", regionInfo.getRegionID(), regionInfo.getgName()
                            .getEn(),
                            regionInfo.getgName()
                                    .getZh());

                    sqlExecutor.insert(Arrays.asList(regionInfo), Region.class);

                    sqlExecutor.insert(globalizationHelper.buildGlobalizationTables(regionInfo, Region.class,
                            regionInfo.getRegionID(), EnumGlobalizationType.Region), GlobalizationTable.class);

                } catch (IOException | SQLException e) {
                    logger.error("Fail to featch region {}.", region.getName(), e);
                }
            });
            for (File constellation : region.listFiles((file) -> file.isDirectory())) {
                taskExecutor.execute(() -> {
                    try {
                        Constellation constellationInfo = yml.toObject(
                                constellation.listFiles(new PatternFilenameFilter("constellation.staticdata"))[0],
                                Constellation.class);
                        Constellation constellationDetail = getConstellation(constellationInfo.getConstellationID());

                        yml.update(constellationInfo, constellationDetail, Constellation.class);

                        logger.info("Feaching constellation {} {} {}.", constellationInfo.getConstellationID(),
                                constellationInfo.getgName()
                                        .getEn(),
                                constellationInfo.getgName()
                                        .getZh());

                        sqlExecutor.insert(Arrays.asList(constellationInfo), Constellation.class);

                        sqlExecutor.insert(
                                globalizationHelper.buildGlobalizationTables(constellationInfo, Constellation.class,
                                        constellationInfo.getConstellationID(), EnumGlobalizationType.Constellation),
                                GlobalizationTable.class);
                    } catch (IOException | SQLException e) {
                        logger.error("Fail to featch constellation {}.", region.getName(), e);
                    }
                });

                for (File solarSystem : constellation.listFiles((file) -> file.isDirectory())) {
                    taskExecutor.execute(() -> {
                        try {
                            SolarSystem solarSystemInfo = yml.toObject(
                                    solarSystem.listFiles(new PatternFilenameFilter("solarsystem.staticdata"))[0],
                                    SolarSystem.class);
                            SolarSystem solarSystemDetail = getSystem(solarSystemInfo.getSolarSystemID());

                            yml.update(solarSystemInfo, solarSystemDetail, SolarSystem.class);

                            logger.info("Feaching solar system {} {} {}.", solarSystemInfo.getSolarSystemID(),
                                    solarSystemInfo.getgName()
                                            .getEn(),
                                    solarSystemInfo.getgName()
                                            .getZh());

                            sqlExecutor.insert(Arrays.asList(solarSystemInfo), SolarSystem.class);

                            sqlExecutor.insert(
                                    globalizationHelper.buildGlobalizationTables(solarSystemInfo, SolarSystem.class,
                                            solarSystemInfo.getSolarSystemID(), EnumGlobalizationType.SolarSystem),
                                    GlobalizationTable.class);
                        } catch (IOException | SQLException e) {
                            logger.error("Fail to featch solar system {}.", region.getName(), e);
                        }
                    });
                }
            }
        }
    }

    @Override
    public Region getRegion(String regionID) {
        return globalizationHelper.queryData((lang) -> {
            return http.doGet(esiConfiguration.getInterfaces()
                    .getRegion()
                    .getUrl(esiConfiguration.getEndpoint())
                    .concat("/")
                    .concat(regionID),
                    new MapBuilder<String, String>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("language", lang.info())
                            .build(),
                    null);
        }, Region.class);
    }

    @Override
    public Constellation getConstellation(String constellationID) {
        return globalizationHelper.queryData((lang) -> {
            return http.doGet(esiConfiguration.getInterfaces()
                    .getConstellation()
                    .getUrl(esiConfiguration.getEndpoint())
                    .concat("/")
                    .concat(constellationID),
                    new MapBuilder<String, String>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("language", lang.info())
                            .build(),
                    null);
        }, Constellation.class);
    }

    @Override
    public SolarSystem getSystem(String systemID) {
        return globalizationHelper.queryData((lang) -> {
            return http.doGet(esiConfiguration.getInterfaces()
                    .getSystem()
                    .getUrl(esiConfiguration.getEndpoint())
                    .concat("/")
                    .concat(systemID),
                    new MapBuilder<String, String>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("language", lang.info())
                            .build(),
                    null);
        }, SolarSystem.class, SolarSystemResponse.class);
    }

    @Override
    public Planet getPlanet(String planetID) {
        return globalizationHelper.queryData((lang) -> {
            return http.doGet(esiConfiguration.getInterfaces()
                    .getPlanet()
                    .getUrl(esiConfiguration.getEndpoint())
                    .concat("/")
                    .concat(planetID),
                    new MapBuilder<String, String>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("language", lang.info())
                            .build(),
                    null);
        }, Planet.class, PlanetResponse.class);
    }

    @Override
    public Moon getMoon(String moonID) {
        return globalizationHelper.queryData((lang) -> {
            return http.doGet(esiConfiguration.getInterfaces()
                    .getMoon()
                    .getUrl(esiConfiguration.getEndpoint())
                    .concat("/")
                    .concat(moonID),
                    new MapBuilder<String, String>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("language", lang.info())
                            .build(),
                    null);
        }, Moon.class, MoonResponse.class);
    }

    @Override
    public Star getStar(String starID) {
        return globalizationHelper.queryData((lang) -> {
            return http.doGet(esiConfiguration.getInterfaces()
                    .getStar()
                    .getUrl(esiConfiguration.getEndpoint())
                    .concat("/")
                    .concat(starID),
                    new MapBuilder<String, String>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("language", lang.info())
                            .build(),
                    null);
        }, Star.class, StarResponse.class);
    }

}
