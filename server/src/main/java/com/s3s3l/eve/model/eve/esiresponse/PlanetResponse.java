/** 
 * Project Name:eve-server 
 * File Name:PlanetResponse.java 
 * Package Name:com.s3s3l.eve.model.eve.esiresponse 
 * Date:Sep 30, 20174:32:06 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.esiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.s3s3l.common.bean.LinkedModel;
import com.s3s3l.eve.model.eve.universe.Planet;

/**
 * <p>
 * </p>
 * ClassName:PlanetResponse <br>
 * Date: Sep 30, 2017 4:32:06 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PlanetResponse implements LinkedModel<Planet>{

    private String name;
    private String planetID;
    private String systemID;
    private String typeID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanetID() {
        return planetID;
    }

    @JsonProperty("planet_id")
    public void setPlanetID(String planetID) {
        this.planetID = planetID;
    }

    public String getSystemID() {
        return systemID;
    }

    @JsonProperty("system_id")
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getTypeID() {
        return typeID;
    }

    @JsonProperty("type_id")
    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    @Override
    public Planet convertTo() {
        Planet p = new Planet();
        p.setName(this.name);
        p.setPlanetID(this.planetID);
        p.setSystemID(this.systemID);
        p.setTypeID(this.typeID);
        return p;
    }

    @Override
    public void convertFrom(Planet model) {
        this.setName(model.getName());
        this.setPlanetID(model.getPlanetID());
        this.setSystemID(model.getSystemID());
        this.setTypeID(model.getTypeID());
    }
}
