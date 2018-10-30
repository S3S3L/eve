/** 
 * Project Name:eve-server 
 * File Name:CategoryInfo.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 201712:52:19 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe.base;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.common.bean.verify.Expectation;
import com.s3s3l.eve.annotation.GlobalizationMap;
import com.s3s3l.eve.annotation.Primary;
import com.s3s3l.eve.model.eve.Globalization;
import com.s3s3l.jdbc.bind.annotation.Column;
import com.s3s3l.jdbc.bind.annotation.Condition;
import com.s3s3l.jdbc.bind.annotation.SqlModel;
import com.s3s3l.jdbc.enumerations.ComparePattern;
import com.s3s3l.jdbc.handler.ArrayTypeHandler;

/**
 * <p>
 * </p>
 * ClassName:CategoryInfo <br>
 * Date: Sep 30, 2017 12:52:19 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@SqlModel(table = "t_constellation")
@JsonInclude(Include.NON_DEFAULT)
public class Constellation extends Astronomical {
    @Primary
    @Condition(forDelete = true)
    @Column(dbType = "varchar(20)")
    @Examine(value = Expectation.HAS_LENGTH, scope = "esi")
    private String constellationID;
    
    @Condition(pattern = ComparePattern.LIKE)
    @Column(dbType = "varchar(20)")
    private String name;
    
    @Column(dbType = "varchar(20)")
    private String nameID;
    
    @Column(dbType = "double")
    private Double radius;
    
    private Position position;
    
    @Condition(forDelete = true)
    @Column(dbType = "varchar(20)")
    private String regionID;
    
    @Column(dbType = "varchar(20) array", typeHandler = ArrayTypeHandler.class)
    private List<String> systems;
    
    @GlobalizationMap("name")
    private Globalization gName;

    public String getConstellationID() {
        return constellationID;
    }

    public void setConstellationID(String constellationID) {
        this.constellationID = constellationID;
    }

    @JsonProperty("constellation_id")
    public void setConstellation_ID(String constellationID) {
        this.constellationID = constellationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    @JsonProperty("region_id")
    public void setRegion_ID(String regionID) {
        this.regionID = regionID;
    }

    public List<String> getSystems() {
        return systems;
    }

    public void setSystems(List<String> systems) {
        this.systems = systems;
    }

    public Globalization getgName() {
        return gName;
    }

    public void setgName(Globalization gName) {
        this.gName = gName;
    }
}
