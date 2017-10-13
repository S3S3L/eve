/** 
 * Project Name:eve-server 
 * File Name:UniverseController.java 
 * Package Name:com.s3s3l.eve.controller 
 * Date:Oct 12, 20177:04:05 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s3s3l.eve.model.eve.universe.Region;
import com.s3s3l.eve.model.eve.universe.SolarSystem;
import com.s3s3l.eve.model.eve.universe.base.Constellation;
import com.s3s3l.eve.service.DBUniverseService;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.resource.JsonHelper;

/**
 * <p>
 * </p>
 * ClassName:UniverseController <br>
 * Date: Oct 12, 2017 7:04:05 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/universe")
public class UniverseController {
    private final JsonHelper json = JacksonUtil.defaultHelper;
    @Autowired
    private DBUniverseService universeService;

    @RequestMapping(value = "/region", method = { RequestMethod.POST, RequestMethod.GET })
    public List<Region> region(@RequestBody(required = false) Region condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return universeService.getRegion(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, Region.class))
                        .orElse(new Region())));
    }

    @RequestMapping(value = "/constellation", method = { RequestMethod.POST, RequestMethod.GET })
    public List<Constellation> constellation(@RequestBody(required = false) Constellation condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return universeService.getConstellation(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, Constellation.class))
                        .orElse(new Constellation())));
    }

    @RequestMapping(value = "/solarSystem", method = { RequestMethod.POST, RequestMethod.GET })
    public List<SolarSystem> solarSystem(@RequestBody(required = false) SolarSystem condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return universeService.getSolarSystem(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, SolarSystem.class))
                        .orElse(new SolarSystem())));
    }

}
