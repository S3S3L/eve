/** 
 * Project Name:eve-server 
 * File Name:UniverseService.java 
 * Package Name:com.s3s3l.eve.service 
 * Date:Sep 29, 20177:00:24 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service;

import java.io.IOException;

import javax.annotation.Nonnull;

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
public interface EsiUniverseService {

    void loadUniverse() throws IOException;

    Region getRegion(@Nonnull Region condition);

    Constellation getConstellation(@Nonnull Constellation condition);

    SolarSystem getSolarSystem(@Nonnull SolarSystem condition);
    
    Planet getPlanet(@Nonnull Planet condition);
    
    Moon getMoon(@Nonnull Moon condition);
    
    Star getStar(@Nonnull Star condition);

}
