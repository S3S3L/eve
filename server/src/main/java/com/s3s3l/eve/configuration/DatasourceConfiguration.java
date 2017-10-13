/** 
 * Project Name:eve-server 
 * File Name:DataSourceConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Sep 15, 20175:08:28 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </p>
 * ClassName:DataSourceConfiguration <br>
 * Date: Sep 15, 2017 5:08:28 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "data")
public class DatasourceConfiguration {

    private String[] blueprint;
    private String[] type;
    private Universe universe;
    private boolean rebuildDatabase;

    public static class Universe {
        private String[] eve;
        private String[] wormhole;

        public String[] getEve() {
            return eve;
        }

        public void setEve(String[] eve) {
            this.eve = eve;
        }

        public String[] getWormhole() {
            return wormhole;
        }

        public void setWormhole(String[] wormhole) {
            this.wormhole = wormhole;
        }
    }

    public String[] getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(String[] blueprint) {
        this.blueprint = blueprint;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public boolean isRebuildDatabase() {
        return rebuildDatabase;
    }

    public void setRebuildDatabase(boolean rebuildDatabase) {
        this.rebuildDatabase = rebuildDatabase;
    }

}
