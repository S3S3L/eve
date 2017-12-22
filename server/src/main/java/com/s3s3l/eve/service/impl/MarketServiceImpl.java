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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.eve.configuration.BusinessConfiguration;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.handler.pagin.PaginRequestHelper;
import com.s3s3l.eve.model.enumetrations.esi.EnumOrderType;
import com.s3s3l.eve.model.enumetrations.eve.EnumCacheScope;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Material;
import com.s3s3l.eve.model.eve.items.Product;
import com.s3s3l.eve.model.eve.market.BlueprintTradeInfo;
import com.s3s3l.eve.model.eve.market.Order;
import com.s3s3l.eve.model.eve.market.TradeInfo;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.eve.service.MarketService;
import com.s3s3l.http.HttpUtil;
import com.s3s3l.utils.collection.MapBuilder;
import com.s3s3l.utils.verify.Verify;

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
    private BusinessConfiguration businessConfiguration;
    @Autowired
    private PaginRequestHelper paginRequestHelper;
    @Autowired
    private HttpUtil http;
    @Autowired
    private ESIConfiguration esiConfiguration;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CacheHelper<String, Object> cache;

    @Override
    public TradeInfo getIncome(String regionID, String blueprintID) {
        Blueprint blueprint = commonService.getBlueprintDetail(blueprintID);
        if (blueprint.getActivities() == null || blueprint.getActivities()
                .getManufacturing() == null || blueprint.getActivities()
                        .getManufacturing()
                        .getProducts() == null) {
            return new TradeInfo();
        }
        List<Product> products = blueprint.getActivities()
                .getManufacturing()
                .getProducts();

        TradeInfo trade = new TradeInfo();
        BigDecimal lowest = BigDecimal.ZERO;

        for (Product product : products) {
            Order order = getOrders(regionID, product.getTypeID(), EnumOrderType.buy).stream()
                    .filter(r -> r.getVolumeRemain() > product.getQuantity())
                    .max(Order::compareTo)
                    .orElse(null);
            if (order == null) {
                break;
            }

            lowest = lowest.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(product.getQuantity())));
        }

        BigDecimal highest = BigDecimal.ZERO;

        for (Product product : products) {
            Order order = getOrders(regionID, product.getTypeID(), EnumOrderType.sell).stream()
                    .min(Order::compareTo)
                    .orElse(null);
            if (order == null) {
                break;
            }

            highest = highest.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(product.getQuantity())));
        }

        trade.setHighest(highest);
        trade.setLowest(lowest);
        return trade;
    }

    @Override
    public TradeInfo getCost(String regionID, String blueprintID) {
        Blueprint blueprint = commonService.getBlueprintDetail(blueprintID);
        if (blueprint.getActivities() == null || blueprint.getActivities()
                .getManufacturing() == null || blueprint.getActivities()
                        .getManufacturing()
                        .getMaterials() == null) {
            return new TradeInfo();
        }
        List<Material> materials = blueprint.getActivities()
                .getManufacturing()
                .getMaterials();

        TradeInfo trade = new TradeInfo();
        BigDecimal highest = BigDecimal.ZERO;

        for (Material material : materials) {
            Order order = getOrders(regionID, material.getTypeID(), EnumOrderType.sell).stream()
                    .filter(r -> r.getVolumeRemain() > material.getQuantity())
                    .min(Order::compareTo)
                    .orElse(null);
            if (order == null) {
                break;
            }

            highest = highest.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(material.getQuantity())));
        }
        BigDecimal lowest = BigDecimal.ZERO;

        for (Material material : materials) {
            Order order = getOrders(regionID, material.getTypeID(), EnumOrderType.buy).stream()
                    .max(Order::compareTo)
                    .orElse(null);
            if (order == null) {
                break;
            }

            lowest = lowest.add(order.getPrice()
                    .multiply(BigDecimal.valueOf(material.getQuantity())));
        }

        trade.setHighest(highest);
        trade.setLowest(lowest);
        return trade;
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

    @Override
    public BlueprintTradeInfo getBlueprintTradInfo(String regionID, String blueprintID) {
        Verify.hasText(regionID);
        Verify.hasText(blueprintID);
        return (BlueprintTradeInfo) ((Map<?, ?>) cache.get(regionID, EnumCacheScope.Business.name())).get(blueprintID);
    }

    @Override
    public List<BlueprintTradeInfo> getBlueprintTradInfoByRegion(String regionID) {
        Verify.hasText(regionID);
        return ((Map<?, ?>) cache.get(regionID, EnumCacheScope.Business.name())).values()
                .stream()
                .map(r -> (BlueprintTradeInfo) r)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlueprintTradeInfo> getBlueprintTradInfoByBlueprint(String blueprintID) {
        Verify.hasText(blueprintID);
        List<BlueprintTradeInfo> result = new ArrayList<>();

        for (String regionID : businessConfiguration.getWatchingRegions()) {
            result.add(getBlueprintTradInfo(regionID, blueprintID));
        }
        return result;
    }

    @Override
    public List<BlueprintTradeInfo> mostValuableBlueprint(String regionID, Long limit) {
        Verify.hasText(regionID);
        Verify.notNull(limit);
        return ((Map<?, ?>) cache.get(regionID, EnumCacheScope.Business.name())).values()
                .stream()
                .map(r -> (BlueprintTradeInfo) r)
                .filter(r -> r != null && r.getIncome() != null && r.getCost() != null)
                .sorted(BlueprintTradeInfo::compareTo)
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .map(r -> {
                    r.setBlueprint(commonService.getBlueprintDetail(r.getBlueprintID()));
                    r.setMaterials(r.getBlueprint()
                            .getActivities()
                            .getManufacturing()
                            .getMaterials()
                            .stream()
                            .map(s -> commonService.getTypeDetail(s.getTypeID()))
                            .collect(Collectors.toList()));
                    r.setProducts(r.getBlueprint()
                            .getActivities()
                            .getManufacturing()
                            .getProducts()
                            .stream()
                            .map(s -> commonService.getTypeDetail(s.getTypeID()))
                            .collect(Collectors.toList()));
                    return r;
                })
                .collect(Collectors.toList());
    }

}
