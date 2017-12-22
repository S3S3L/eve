/** 
 * Project Name:eve-server 
 * File Name:BlueprintTradeInfo.java 
 * Package Name:com.s3s3l.eve.model.eve.market 
 * Date:Oct 17, 20177:31:39 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.market;

import java.util.List;

import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Type;

/**
 * <p>
 * </p>
 * ClassName:BlueprintTradeInfo <br>
 * Date: Oct 17, 2017 7:31:39 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class BlueprintTradeInfo implements Comparable<BlueprintTradeInfo> {

    private String blueprintID;
    private String regionID;
    private TradeInfo cost;
    private TradeInfo sell;
    private TradeInfo income;
    private Blueprint blueprint;
    private List<Type> materials;
    private List<Type> products;

    public String getBlueprintID() {
        return blueprintID;
    }

    public BlueprintTradeInfo setBlueprintID(String blueprintID) {
        this.blueprintID = blueprintID;
        return this;
    }

    public String getRegionID() {
        return regionID;
    }

    public BlueprintTradeInfo setRegionID(String regionID) {
        this.regionID = regionID;
        return this;
    }

    public TradeInfo getCost() {
        return cost;
    }

    public BlueprintTradeInfo setCost(TradeInfo cost) {
        this.cost = cost;
        return this;
    }

    public TradeInfo getSell() {
        return sell;
    }

    public BlueprintTradeInfo setSell(TradeInfo sell) {
        this.sell = sell;
        return this;
    }

    public TradeInfo getIncome() {
        return income;
    }

    public BlueprintTradeInfo setIncome(TradeInfo income) {
        this.income = income;
        return this;
    }

    public BlueprintTradeInfo calculateIncome() {
        if (cost == null || sell == null) {
            return this;
        }

        this.income = new TradeInfo();
        income.setHighest(sell.getHighest()
                .subtract(cost.getLowest()));
        income.setLowest(sell.getLowest()
                .subtract(cost.getHighest()));

        return this;
    }

    @Override
    public int compareTo(BlueprintTradeInfo o) {
        if (this.income == null || this.income.getLowest() == null) {
            return -1;
        }
        if (o == null || o.income == null || o.getIncome()
                .getLowest() == null) {
            return 1;
        }
        return this.getIncome()
                .getLowest()
                .compareTo(o.getIncome()
                        .getLowest());
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    public List<Type> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Type> materials) {
        this.materials = materials;
    }

    public List<Type> getProducts() {
        return products;
    }

    public void setProducts(List<Type> products) {
        this.products = products;
    }
}
