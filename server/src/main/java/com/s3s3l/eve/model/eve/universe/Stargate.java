/** 
 * Project Name:eve-server 
 * File Name:Stargate.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 20172:14:44 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p>
 * 星门
 * </p>
 * ClassName:Stargate <br>
 * Date: Sep 30, 2017 2:14:44 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class Stargate {

    /**
     * 目的地（星门id）
     */
    private String destination;
    private Double[] position;
    private String typeID;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
}
