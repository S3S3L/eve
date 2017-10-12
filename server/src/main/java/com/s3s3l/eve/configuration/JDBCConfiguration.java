/** 
 * Project Name:eve-server 
 * File Name:JDBCConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Oct 9, 20171:35:16 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.configuration;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </p> 
 * ClassName:JDBCConfiguration <br> 
 * Date:     Oct 9, 2017 1:35:16 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@ConditionalOnClass(PoolProperties.class)
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class JDBCConfiguration extends PoolProperties {

    /**
     * @since JDK 1.8
     */
    private static final long serialVersionUID = 6502361085513435695L;
    private boolean cryptographic;

    public boolean isCryptographic() {
        return cryptographic;
    }

    public void setCryptographic(boolean cryptographic) {
        this.cryptographic = cryptographic;
    }

}