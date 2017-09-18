/** 
 * Project Name:eve-server 
 * File Name:DataSourceConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Sep 15, 20175:08:28 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </p> 
 * ClassName:DataSourceConfiguration <br> 
 * Date:     Sep 15, 2017 5:08:28 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "data")
public class DataSourceConfiguration {

    private List<String> blueprint;

    public List<String> getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(List<String> blueprint) {
        this.blueprint = blueprint;
    }
}
  