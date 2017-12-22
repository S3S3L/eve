/** 
 * Project Name:eve-server 
 * File Name:UniverseService.java 
 * Package Name:com.s3s3l.eve.service 
 * Date:Sep 29, 20177:00:24 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service;

import java.util.List;

import javax.annotation.Nonnull;

import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.eve.model.eve.universe.Moon;
import com.s3s3l.eve.model.eve.universe.Planet;
import com.s3s3l.eve.model.eve.universe.Region;
import com.s3s3l.eve.model.eve.universe.SolarSystem;
import com.s3s3l.eve.model.eve.universe.Star;
import com.s3s3l.eve.model.eve.universe.base.Constellation;

/**
 * <p>
 * </p>
 * ClassName:UniverseService <br>
 * Date: Sep 29, 2017 7:00:24 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface DBUniverseService {

    List<Region> getRegion(@Nonnull Region condition);

    List<Constellation> getConstellation(@Nonnull Constellation condition);

    List<SolarSystem> getSolarSystem(@Nonnull SolarSystem condition);
    
    List<Planet> getPlanet(@Nonnull Planet condition);
    
    List<Moon> getMoon(@Nonnull Moon condition);
    
    List<Star> getStar(@Nonnull Star condition);

    @Examine
    Region getRegionDetail(@Nonnull String regionID);

    @Examine
    Constellation getConstellationDetail(@Nonnull String constellationID);

    @Examine
    SolarSystem getSolarSystemDetail(@Nonnull String solarSystemID);

    @Examine
    Planet getPlanetDetail(@Nonnull String planetID);

    @Examine
    Moon getMoonDetail(@Nonnull String moonID);

    @Examine
    Star getStarDetail(@Nonnull String starID);

}
