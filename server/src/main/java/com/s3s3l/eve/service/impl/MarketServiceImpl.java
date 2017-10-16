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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.handler.PaginRequestHelper;
import com.s3s3l.eve.model.enumetrations.esi.EnumOrderType;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Material;
import com.s3s3l.eve.model.eve.items.Product;
import com.s3s3l.eve.model.eve.market.Order;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.eve.service.MarketService;
import com.s3s3l.http.HttpUtil;
import com.s3s3l.utils.collection.MapBuilder;

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
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private PaginRequestHelper paginRequestHelper;
    @Autowired
    private HttpUtil http;
    @Autowired
    private ESIConfiguration esiConfiguration;
    @Autowired
    private CommonService commonService;

    @Override
    public BigDecimal getIncome(String regionID, String blueprintID) {
        Blueprint blueprint = commonService.getBlueprintDetail(blueprintID);
        List<Product> products = blueprint.getActivities()
                .getManufacturing()
                .getProducts();

        BigDecimal income = BigDecimal.ZERO;

        for (Product product : products) {
            Order order = getOrders(regionID, product.getTypeID(), EnumOrderType.buy).stream()
                    .filter(r -> r.getVolumeRemain() > product.getQuantity())
                    .max(Order::compareTo)
                    .orElse(null);
            if (order == null) {
                return BigDecimal.ZERO;
            }

            income = income.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(product.getQuantity())));
        }
        return income;
    }

    @Override
    public BigDecimal getCost(String regionID, String blueprintID) {
        Blueprint blueprint = commonService.getBlueprintDetail(blueprintID);
        List<Material> materials = blueprint.getActivities()
                .getManufacturing()
                .getMaterials();

        BigDecimal cost = BigDecimal.ZERO;

        for (Material material : materials) {
            Order order = getOrders(regionID, material.getTypeID(), EnumOrderType.sell).stream()
                    .filter(r -> r.getVolumeRemain() > material.getQuantity())
                    .min(Order::compareTo)
                    .orElse(null);
            if (order == null) {
                return BigDecimal.ZERO;
            }

            cost = cost.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(material.getQuantity())));
        }
        return cost;
    }

    @Override
    public List<Order> getOrders(String regionID, String typeID, EnumOrderType orderType) {
        return paginRequestHelper.queryAll((page) -> {
            return http.doGet(new MapBuilder<String, String>(new HashMap<>()).put("region_id", regionID)
                    .build(),
                    esiConfiguration.getInterfaces()
                            .getOrders()
                            .getUrl(esiConfiguration.getEndpoint()),
                    new MapBuilder<String, Object>(new HashMap<>()).put("datasource", esiConfiguration.getDatasource()
                            .info())
                            .put("page", String.valueOf(page))
                            .put("order_type", Optional.ofNullable(orderType)
                                    .map(r -> r.name())
                                    .orElse(null))
                            .put("type_id", typeID)
                            .build(),
                    null);
        }, Order.class);
    }

}
