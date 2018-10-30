/** 
 * Project Name:eve-server 
 * File Name:Moon.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 20172:05:34 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.common.bean.verify.Expectation;
import com.s3s3l.eve.model.eve.universe.base.PlanetAttributes;
import com.s3s3l.eve.model.eve.universe.base.Statistics;

/**
 * <p>
 * </p>
 * ClassName:Moon <br>
 * Date: Sep 30, 2017 2:05:34 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class Moon {

    private PlanetAttributes planetAttributes;
    private Double[] position;
    private Double radius;
    private Statistics statistics;
    private String typeID;
    @Examine(value = Expectation.NOT_EMPTY, scope = "esi")
    private String moonID;
    private String name;
    private String systemID;

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

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getMoonID() {
        return moonID;
    }

    public void setMoonID(String moonID) {
        this.moonID = moonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }
}
