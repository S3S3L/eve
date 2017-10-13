/** 
 * Project Name:eve-server 
 * File Name:InterceptorComponent.java 
 * Package Name:com.s3s3l.eve.component 
 * Date:Oct 12, 20177:26:01 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.s3s3l.eve.handler.LogInterceptor;

/**
 * <p>
 * </p> 
 * ClassName:InterceptorComponent <br> 
 * Date:     Oct 12, 2017 7:26:01 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Component
public class InterceptorComponent extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }
}
  