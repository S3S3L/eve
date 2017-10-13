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
import java.util.List;

import com.s3s3l.eve.model.enumetrations.esi.EnumOrderType;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.market.Order;

/**
 * <p>
 * </p>
 * ClassName:MarketService <br>
 * Date: Sep 29, 2017 3:22:40 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface MarketService {
    
    void loadItems();
    
    void loadBluePrints();

    BigDecimal getCost(String regionID, Blueprint blueprint);

    List<Order> getOrders(String regionID,String typeID, EnumOrderType orderType);
}
