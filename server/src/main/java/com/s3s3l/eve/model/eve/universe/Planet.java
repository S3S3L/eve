/** 
 * Project Name:eve-server 
 * File Name:PlanetInfo.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 20171:47:06 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.common.bean.verify.Expectation;
import com.s3s3l.eve.model.eve.universe.base.PlanetAttributes;
import com.s3s3l.eve.model.eve.universe.base.Statistics;

/**
 * <p>
 * </p>
 * ClassName:PlanetInfo <br>
 * Date: Sep 30, 2017 1:47:06 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class Planet {

    private String typeID;
    private Integer celestialIndex;
    /**
     * 行星参数
     */
    private PlanetAttributes planetAttributes;
    private Double[] position;
    private Double radius;
    /**
     * 静态数据
     */
    private Statistics statistics;
    private Map<String, AsteroidBelt> asteroidBelts;
    private Map<String, Moon> moons;
    private String name;
    @Examine(value = Expectation.NOT_EMPTY, scope = "esi")
    private String planetID;
    private String systemID;

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public Integer getCelestialIndex() {
        return celestialIndex;
    }

    public void setCelestialIndex(Integer celestialIndex) {
        this.celestialIndex = celestialIndex;
    }

    public PlanetAttributes getPlanetAttributes() {
        return planetAttributes;
    }

    public void setPlanetAttributes(PlanetAttributes planetAttributes) {
        this.planetAttributes = planetAttributes;
    }

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Map<String, AsteroidBelt> getAsteroidBelts() {
        return asteroidBelts;
    }

    public void setAsteroidBelts(Map<String, AsteroidBelt> asteroidBelts) {
        this.asteroidBelts = asteroidBelts;
    }

    public Map<String, Moon> getMoons() {
        return moons;
    }

    public void setMoons(Map<String, Moon> moons) {
        this.moons = moons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanetID() {
        return planetID;
    }

    public void setPlanetID(String planetID) {
        this.planetID = planetID;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }
}
