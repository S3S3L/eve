/** 
 * Project Name:eve-server 
 * File Name:RegionInfo.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 29, 20175:53:59 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.common.bean.verify.Expectation;
import com.s3s3l.eve.annotation.GlobalizationMap;
import com.s3s3l.eve.annotation.Primary;
import com.s3s3l.eve.model.eve.Globalization;
import com.s3s3l.eve.model.eve.universe.base.Astronomical;
import com.s3s3l.jdbc.bind.annotation.Column;
import com.s3s3l.jdbc.bind.annotation.Condition;
import com.s3s3l.jdbc.bind.annotation.SqlModel;
import com.s3s3l.jdbc.enumerations.ComparePattern;
import com.s3s3l.jdbc.handler.ArrayTypeHandler;
import com.s3s3l.jdbc.helper.ArrayHelper;

/**
 * <p>
 * </p>
 * ClassName:RegionInfo <br>
 * Date: Sep 29, 2017 5:53:59 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@SqlModel(table = "t_region")
@JsonInclude(Include.NON_DEFAULT)
public class Region extends Astronomical {

    @Primary
    @Condition(forDelete = true)
    @Column(dbType = "varchar(20)")
    @Examine(value = Expectation.HAS_LENGTH, scope = "esi")
    private String regionID;

    @Condition(pattern = ComparePattern.LIKE)
    @Column(dbType = "varchar(20)")
    private String name;

    @Column(dbType = "varchar(20) array", typeHandler = ArrayTypeHandler.class)
    private List<String> constellations;

    @Column(dbType = "longvarchar")
    private String description;

    @Column(dbType = "varchar(20)")
    private String nameID;

    @Column(dbType = "varchar(20)")
    private String nebula;

    @Column(dbType = "varchar(20)")
    private String wormholeClassID;

    @GlobalizationMap("name")
    private Globalization gName;

    @GlobalizationMap("description")
    private Globalization gDescription;


    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    @JsonProperty(value = "region_id")
    public void setRegion_ID(String regionID) {
        this.regionID = regionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getConstellations() {
        return constellations;
    }

    public void setConstellations(List<String> constellations) {
        this.constellations = constellations;
    }

    public String getConstellationsArray() {
        return ArrayHelper.toArray(constellations);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public String getNebula() {
        return nebula;
    }

    public void setNebula(String nebula) {
        this.nebula = nebula;
    }

    public String getWormholeClassID() {
        return wormholeClassID;
    }

    public void setWormholeClassID(String wormholeClassID) {
        this.wormholeClassID = wormholeClassID;
    }

    public Globalization getgName() {
        return gName;
    }

    public void setgName(Globalization gName) {
        this.gName = gName;
    }

    public Globalization getgDescription() {
        return gDescription;
    }

    public void setgDescription(Globalization gDescription) {
        this.gDescription = gDescription;
    }
}
