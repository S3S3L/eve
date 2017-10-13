/** 
 * Project Name:eve-server 
 * File Name:FilterRegistrationConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Oct 12, 20178:03:55 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.s3s3l.eve.filter.CrosFilter;

/**
 * <p>
 * </p> 
 * ClassName:FilterRegistrationConfiguration <br> 
 * Date:     Oct 12, 2017 8:03:55 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Configuration
public class FilterRegistrationConfiguration {


    @Bean
    public FilterRegistrationBean crosFilterRegistration(@Qualifier("crosFilter") Filter crosFilter) {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(crosFilter);
        registration.addUrlPatterns("/*");
        registration.setName("crosFilter");
        registration.setOrder(1);
        return registration;
    }
    
    @Bean(name = "crosFilter")
    public Filter crosFilter() {
        return new CrosFilter();
    }
}
  