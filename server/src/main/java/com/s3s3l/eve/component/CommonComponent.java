/** 
 * Project Name:eve-server 
 * File Name:CommonComponent.java 
 * Package Name:com.s3s3l.eve.component 
 * Date:Sep 8, 201712:00:44 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.component;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.s3s3l.http.HttpUtil;

/**
 * <p>
 * </p>
 * ClassName:CommonComponent <br>
 * Date: Sep 8, 2017 12:00:44 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
public class CommonComponent {

    @Bean
    public HttpUtil httpUtil() {
        return new HttpUtil();
    }

    @Bean(name = "expireCache")
    public Cache<String, String> expireCache() {
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .weakKeys()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(600, TimeUnit.SECONDS)
                .build();
    }

    @Primary
    @Bean
    public Cache<String,String> commonCache(){
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .weakKeys()
                .maximumSize(Integer.MAX_VALUE)
                .build();
    }
}
