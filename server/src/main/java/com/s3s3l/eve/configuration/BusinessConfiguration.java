/** 
 * Project Name:eve-server 
 * File Name:BusinessConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Oct 17, 20175:48:51 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </p> 
 * ClassName:BusinessConfiguration <br> 
 * Date:     Oct 17, 2017 5:48:51 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "business")
public class BusinessConfiguration {

    private String[] watchingRegions;

    public String[] getWatchingRegions() {
        return watchingRegions;
    }

    public void setWatchingRegions(String[] watchingRegions) {
        this.watchingRegions = watchingRegions;
    }
}
  