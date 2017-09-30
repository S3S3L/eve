/** 
 * Project Name:eve-server 
 * File Name:MarketServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Sep 29, 20173:24:19 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.service.MarketService;
import com.s3s3l.http.HttpUtil;

/**
 * <p>
 * </p>
 * ClassName:MarketServiceImpl <br>
 * Date: Sep 29, 2017 3:24:19 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class MarketServiceImpl implements MarketService {
    @Autowired
    private HttpUtil http;

    @Override
    public BigDecimal getCost(Blueprint blueprint) {
        return null;
    }

}
