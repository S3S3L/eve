/** 
 * Project Name:eve-server 
 * File Name:SystemInfo.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 20171:35:21 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.eve.annotation.GlobalizationMap;
import com.s3s3l.eve.annotation.Primary;
import com.s3s3l.eve.model.eve.Globalization;
import com.s3s3l.eve.model.eve.universe.base.Astronomical;
import com.s3s3l.eve.model.eve.universe.base.Position;
import com.s3s3l.jdbc.bind.annotation.Column;
import com.s3s3l.jdbc.bind.annotation.SqlModel;

/**
 * <p>
 * </p>
 * ClassName:SystemInfo <br>
 * Date: Sep 30, 2017 1:35:21 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@SqlModel(table = "t_solar_system")
@JsonInclude(Include.NON_DEFAULT)
public class SolarSystem extends Astronomical {

    /**
     * 是否有走廊(跳桥？)
     */
    @Column(dbType = "boolean")
    private Boolean corridor;
    /**
     * 是否边缘
     */
    @Column(dbType = "boolean")
    private Boolean fringe;
    /**
     * 是否有hub
     */
    @Column(dbType = "boolean")
    private Boolean hub;
    @Column(dbType = "boolean")
    private Boolean international;
    @Column(dbType = "double")
    private Double luminosity;
    /**
     * 行星
     */
    private Map<String, Planet> planets;
    /**
     * 半径
     */
    @Column(dbType = "double")
    private Double radius;
    @Column(dbType = "boolean")
    private Boolean regional;
    /**
     * 安等
     */
    @Column(dbType = "numeric")
    private BigDecimal security;
    @Column(dbType = "varchar(20)")
    private String securityClass;
    @Primary
    @Column(dbType = "varchar(20)")
    private String solarSystemID;
    @Column(dbType = "varchar(20)")
    private String solarSystemNameID;
    /**
     * 恒星
     */
    private Star star;
    /**
     * 星门
     */
    private Map<String, Stargate> stargates;
    @Column(dbType = "varchar(20)")
    private String sunTypeID;
    @Column(dbType = "varchar(20)")
    private String wormholeClassID;
    @Column(dbType = "varchar(20)")
    private String constellationID;
    @Column(dbType = "varchar(20)")
    private String name;
    private Position position;
    @Column(dbType = "varchar(20)")
    private String starID;
    @Column(dbType = "varchar(20)")
    private String systemID;
    @GlobalizationMap("name")
    private Globalization gName;

    public Boolean getCorridor() {
        return corridor;
    }

    public void setCorridor(Boolean corridor) {
        this.corridor = corridor;
    }

    public Boolean getFringe() {
        return fringe;
    }

    public void setFringe(Boolean fringe) {
        this.fringe = fringe;
    }

    public Boolean getHub() {
        return hub;
    }

    public void setHub(Boolean hub) {
        this.hub = hub;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public Double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Double luminosity) {
        this.luminosity = luminosity;
    }

    public Map<String, Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Map<String, Planet> planets) {
        this.planets = planets;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Boolean getRegional() {
        return regional;
    }

    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

    public BigDecimal getSecurity() {
        return security;
    }

    public void setSecurity(BigDecimal security) {
        this.security = security;
    }

    public String getSecurityClass() {
        return securityClass;
    }

    public void setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
    }

    public String getSolarSystemID() {
        return solarSystemID;
    }

    public void setSolarSystemID(String solarSystemID) {
        this.solarSystemID = solarSystemID;
    }

    public String getSolarSystemNameID() {
        return solarSystemNameID;
    }

    public void setSolarSystemNameID(String solarSystemNameID) {
        this.solarSystemNameID = solarSystemNameID;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Map<String, Stargate> getStargates() {
        return stargates;
    }

    public void setStargates(Map<String, Stargate> stargates) {
        this.stargates = stargates;
    }

    public String getSunTypeID() {
        return sunTypeID;
    }

    public void setSunTypeID(String sunTypeID) {
        this.sunTypeID = sunTypeID;
    }

    public String getWormholeClassID() {
        return wormholeClassID;
    }

    public void setWormholeClassID(String wormholeClassID) {
        this.wormholeClassID = wormholeClassID;
    }

    public String getConstellationID() {
        return constellationID;
    }

    public void setConstellationID(String constellationID) {
        this.constellationID = constellationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getStarID() {
        return starID;
    }

    public void setStarID(String starID) {
        this.starID = starID;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public Globalization getgName() {
        return gName;
    }

    public void setgName(Globalization gName) {
        this.gName = gName;
    }

}
