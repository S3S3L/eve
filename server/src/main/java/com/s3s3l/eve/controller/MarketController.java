/** 
 * Project Name:eve-server 
 * File Name:MarketController.java 
 * Package Name:com.s3s3l.eve.controller 
 * Date:Oct 16, 201711:25:47 AM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s3s3l.eve.model.enumetrations.esi.EnumOrderType;
import com.s3s3l.eve.model.eve.market.BlueprintTradeInfo;
import com.s3s3l.eve.model.eve.market.Order;
import com.s3s3l.eve.model.eve.market.TradeInfo;
import com.s3s3l.eve.service.MarketService;

/**
 * <p>
 * </p>
 * ClassName:MarketController <br>
 * Date: Oct 16, 2017 11:25:47 AM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @RequestMapping(value = "/orderList", method = { RequestMethod.GET })
    public List<Order> orderList(@RequestParam(name = "regionID") String regionID,
            @RequestParam(name = "typeID") String typeID,
            @RequestParam(name = "orderType") EnumOrderType orderType) {
        return marketService.getOrders(regionID, typeID, orderType);
    }

    @RequestMapping(value = "/cost", method = { RequestMethod.GET })
    public TradeInfo cost(@RequestParam(name = "regionID") String regionID,
            @RequestParam(name = "blueprintID") String blueprintID) {
        return marketService.getCost(regionID, blueprintID);
    }

    @RequestMapping(value = "/income", method = { RequestMethod.GET })
    public TradeInfo income(@RequestParam(name = "regionID") String regionID,
            @RequestParam(name = "blueprintID") String blueprintID) {
        return marketService.getIncome(regionID, blueprintID);
    }

    @RequestMapping(value = "/blueprintTradInfoByRegion", method = { RequestMethod.GET })
    public List<BlueprintTradeInfo> getBlueprintTradInfoByRegion(@RequestParam(name = "regionID") String regionID) {
        return marketService.getBlueprintTradInfoByRegion(regionID);
    }

    @RequestMapping(value = "/mostValuableBlueprint", method = { RequestMethod.GET })
    public List<BlueprintTradeInfo> mostValuableBlueprint(@RequestParam(name = "regionID") String regionID,
            @RequestParam(name = "limit") Long limit) {
        return marketService.mostValuableBlueprint(regionID, limit);
    }
}
