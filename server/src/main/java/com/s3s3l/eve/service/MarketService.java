/** 
 * Project Name:eve-server 
 * File Name:MarketService.java 
 * Package Name:com.s3s3l.eve.service 
 * Date:Sep 29, 20173:22:40 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service;

import java.util.List;

import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.eve.model.enumetrations.esi.EnumOrderType;
import com.s3s3l.eve.model.eve.market.BlueprintTradeInfo;
import com.s3s3l.eve.model.eve.market.Order;
import com.s3s3l.eve.model.eve.market.TradeInfo;

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

    @Examine
    TradeInfo getIncome(String regionID, String blueprintID);

    @Examine
    TradeInfo getCost(String regionID, String blueprintID);

    @Examine
    List<Order> getOrders(String regionID, String typeID, EnumOrderType orderType);

    @Examine
    BlueprintTradeInfo getBlueprintTradInfo(String regionID, String blueprintID);

    @Examine
    List<BlueprintTradeInfo> getBlueprintTradInfoByRegion(String regionID);

    @Examine
    List<BlueprintTradeInfo> getBlueprintTradInfoByBlueprint(String blueprintID);
    
    @Examine
    List<BlueprintTradeInfo> mostValuableBlueprint(String regionID, Long limit);
    
}
