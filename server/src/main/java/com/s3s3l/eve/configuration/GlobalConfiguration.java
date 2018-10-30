/** 
 * Project Name:eve-server 
 * File Name:GlobalConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Oct 13, 20176:57:34 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </p> 
 * ClassName:GlobalConfiguration <br> 
 * Date:     Oct 13, 2017 6:57:34 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfiguration {

    private int executorThreadCount = 50;

    public int getExecutorThreadCount() {
        return executorThreadCount;
    }

    public void setExecutorThreadCount(int executorThreadCount) {
        this.executorThreadCount = executorThreadCount;
    }
}
  