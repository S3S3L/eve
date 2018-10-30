/** 
 * Project Name:eve-server 
 * File Name:SolarSystemResponse.java 
 * Package Name:com.s3s3l.eve.model.eve.esiresponse 
 * Date:Sep 30, 20173:49:17 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.esiresponse;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.s3s3l.common.bean.LinkedModel;
import com.s3s3l.eve.model.eve.universe.SolarSystem;

/**
 * <p>
 * </p>
 * ClassName:SolarSystemResponse <br>
 * Date: Sep 30, 2017 3:49:17 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class SolarSystemResponse implements LinkedModel<SolarSystem> {

    private String constellationID;
    private String name;
    private String securityClass;
    private BigDecimal securityStatus;
    private String starID;
    private String systemID;

    public String getConstellationID() {
        return constellationID;
    }

    @JsonProperty("constellation_id")
    public void setConstellationID(String constellationID) {
        this.constellationID = constellationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityClass() {
        return securityClass;
    }

    @JsonProperty("security_class")
    public void setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
    }

    public BigDecimal getSecurityStatus() {
        return securityStatus;
    }

    @JsonProperty("security_status")
    public void setSecurityStatus(BigDecimal securityStatus) {
        this.securityStatus = securityStatus;
    }

    public String getStarID() {
        return starID;
    }

    @JsonProperty("star_id")
    public void setStarID(String starID) {
        this.starID = starID;
    }

    public String getSystemID() {
        return systemID;
    }

    @JsonProperty("system_id")
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    @Override
    public SolarSystem convertTo() {
        SolarSystem ss = new SolarSystem();
        ss.setConstellationID(this.constellationID);
        ss.setName(this.name);
        ss.setSecurity(this.securityStatus);
        ss.setSecurityClass(this.securityClass);
        ss.setStarID(this.starID);
        ss.setSystemID(this.systemID);
        return ss;
    }

    @Override
    public void convertFrom(SolarSystem model) {
        this.setConstellationID(model.getConstellationID());
        this.setName(model.getName());
        this.setSecurityStatus(model.getSecurity());
        this.setSecurityClass(model.getSecurityClass());
        this.setStarID(model.getStarID());
        this.setSystemID(model.getSystemID());
    }
}
