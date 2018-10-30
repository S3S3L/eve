/** 
 * Project Name:eve-server 
 * File Name:StarResponse.java 
 * Package Name:com.s3s3l.eve.model.eve.esiresponse 
 * Date:Sep 30, 20175:05:48 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.esiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.s3s3l.common.bean.LinkedModel;
import com.s3s3l.eve.model.eve.universe.Star;

/**
 * <p>
 * </p>
 * ClassName:StarResponse <br>
 * Date: Sep 30, 2017 5:05:48 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class StarResponse implements LinkedModel<Star> {

    private Double age;
    private Double luminosity;
    private String name;
    private Double radius;
    private String systemID;
    private String spectralClass;
    private Double temperature;
    private String typeID;

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Double luminosity) {
        this.luminosity = luminosity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public String getSystemID() {
        return systemID;
    }

    @JsonProperty("solar_system_id")
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getSpectralClass() {
        return spectralClass;
    }

    @JsonProperty("spectral_class")
    public void setSpectralClass(String spectralClass) {
        this.spectralClass = spectralClass;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getTypeID() {
        return typeID;
    }

    @JsonProperty("type_id")
    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    @Override
    public Star convertTo() {
        Star s = new Star();
        s.getStatistics()
                .setAge(this.age);
        s.getStatistics()
                .setLuminosity(this.luminosity);
        s.setName(this.name);
        s.setRadius(this.radius);
        s.setSystemID(this.systemID);
        s.getStatistics()
                .setSpectralClass(this.spectralClass);
        s.getStatistics()
                .setTemperature(this.temperature);
        s.setTypeID(this.typeID);
        return s;
    }

    @Override
    public void convertFrom(Star model) {
        this.setAge(model.getStatistics().getAge());
        this.setLuminosity(model.getStatistics().getLuminosity());
        this.setName(model.getName());
        this.setRadius(model.getRadius());
        this.setSystemID(model.getSystemID());
        this.setSpectralClass(model.getStatistics().getSpectralClass());
        this.setTemperature(model.getStatistics().getTemperature());
        this.setTypeID(model.getTypeID());
    }
}
