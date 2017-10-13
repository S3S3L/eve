/** 
 * Project Name:eve-server 
 * File Name:DBUniverseServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Oct 12, 20175:46:37 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.eve.model.enumetrations.eve.EnumCacheScope;
import com.s3s3l.eve.model.eve.universe.Moon;
import com.s3s3l.eve.model.eve.universe.Planet;
import com.s3s3l.eve.model.eve.universe.Region;
import com.s3s3l.eve.model.eve.universe.SolarSystem;
import com.s3s3l.eve.model.eve.universe.Star;
import com.s3s3l.eve.model.eve.universe.base.Constellation;
import com.s3s3l.eve.service.DBUniverseService;
import com.s3s3l.jdbc.exec.SqlExecutor;

/**
 * <p>
 * </p>
 * ClassName:DBUniverseServiceImpl <br>
 * Date: Oct 12, 2017 5:46:37 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class DBUniverseServiceImpl implements DBUniverseService {

    @Autowired
    private CacheHelper<String, Object> cache;
    @Autowired
    private SqlExecutor sqlExecutor;

    @Override
    public List<Region> getRegion(Region condition) {
        return sqlExecutor.select(condition, Region.class);
    }

    @Override
    public List<Constellation> getConstellation(Constellation condition) {
        return sqlExecutor.select(condition, Constellation.class);
    }

    @Override
    public List<SolarSystem> getSolarSystem(SolarSystem condition) {
        return sqlExecutor.select(condition, SolarSystem.class);
    }

    @Override
    public List<Planet> getPlanet(Planet condition) {
        return sqlExecutor.select(condition, Planet.class);
    }

    @Override
    public List<Moon> getMoon(Moon condition) {
        return sqlExecutor.select(condition, Moon.class);
    }

    @Override
    public List<Star> getStar(Star condition) {
        return sqlExecutor.select(condition, Star.class);
    }

    @Override
    public Region getRegionDetail(String regionID) {
        return (Region) cache.get(regionID, EnumCacheScope.Region.name());
    }

    @Override
    public Constellation getConstellationDetail(String constellationID) {
        return (Constellation) cache.get(constellationID, EnumCacheScope.Constellation.name());
    }

    @Override
    public SolarSystem getSolarSystemDetail(String solarSystemID) {
        return (SolarSystem) cache.get(solarSystemID, EnumCacheScope.SolarSystem.name());
    }

    @Override
    public Planet getPlanetDetail(String planetID) {
        return (Planet) cache.get(planetID, EnumCacheScope.Planet.name());
    }

    @Override
    public Moon getMoonDetail(String moonID) {
        return (Moon) cache.get(moonID, EnumCacheScope.Moon.name());
    }

    @Override
    public Star getStarDetail(String starID) {
        return (Star) cache.get(starID, EnumCacheScope.Star.name());
    }

}
