/** 
 * Project Name:eve-server 
 * File Name:MoonResponse.java 
 * Package Name:com.s3s3l.eve.model.eve.esiresponse 
 * Date:Sep 30, 20174:49:56 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.esiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.s3s3l.common.bean.LinkedModel;
import com.s3s3l.eve.model.eve.universe.Moon;

/**
 * <p>
 * </p>
 * ClassName:MoonResponse <br>
 * Date: Sep 30, 2017 4:49:56 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class MoonResponse implements LinkedModel<Moon> {

    private String moonID;
    private String name;
    private String systemID;

    public String getMoonID() {
        return moonID;
    }

    @JsonProperty("moon_id")
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

    @JsonProperty("system_id")
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    @Override
    public Moon convertTo() {
        Moon m = new Moon();
        m.setName(this.name);
        m.setMoonID(this.moonID);
        m.setSystemID(this.systemID);
        return m;
    }

    @Override
    public void convertFrom(Moon model) {
        this.setMoonID(model.getMoonID());
        this.setName(model.getName());
        this.setSystemID(model.getSystemID());
    }
}
