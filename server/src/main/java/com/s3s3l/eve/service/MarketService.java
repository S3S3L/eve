/** 
 * Project Name:eve-server 
 * File Name:MarketService.java 
 * Package Name:com.s3s3l.eve.service 
 * Date:Sep 29, 20173:22:40 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.service;

import java.math.BigDecimal;

import com.s3s3l.eve.model.eve.items.Blueprint;

/**
 * <p>
 * </p> 
 * ClassName:MarketService <br> 
 * Date:     Sep 29, 2017 3:22:40 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public interface MarketService {

    BigDecimal getCost(Blueprint blueprint);
}
  