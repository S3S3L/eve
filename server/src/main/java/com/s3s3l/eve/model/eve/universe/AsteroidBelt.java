/** 
 * Project Name:eve-server 
 * File Name:AsteroidBelt.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 20171:57:27 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.eve.model.eve.universe.base.Statistics;

/**
 * <p>
 * 小行星带
 * </p> 
 * ClassName:AsteroidBelt <br> 
 * Date:     Sep 30, 2017 1:57:27 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class AsteroidBelt {

    private Double[] position;
    private Statistics statistics;
    private String typeID;

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
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
}
