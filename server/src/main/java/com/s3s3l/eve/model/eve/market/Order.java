/** 
 * Project Name:eve-server 
 * File Name:Order.java 
 * Package Name:com.s3s3l.eve.model.eve.market 
 * Date:Oct 12, 201712:16:43 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.market;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p>
 * </p>
 * ClassName:Order <br>
 * Date: Oct 12, 2017 12:16:43 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class Order implements Comparable<Order>{

    private String orderID;
    private String typeID;
    private String locationID;
    /**
     * 总数
     */
    private Integer volumeTotal;
    /**
     * 剩余
     */
    private Integer volumeRemain;
    /**
     * 最小数量
     */
    private Integer minVolume;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 是否是购买订单
     */
    private Boolean isByOrder;
    /**
     * 剩余时间（天）
     */
    private Integer duration;
    /**
     * 发布时间
     */
    private Timestamp issued;
    private String range;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @JsonProperty(value = "order_id")
    public void setOrder_ID(String orderID) {
        this.orderID = orderID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    @JsonProperty(value = "type_id")
    public void setType_ID(String typeID) {
        this.typeID = typeID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    @JsonProperty(value = "location_id")
    public void setLocation_ID(String locationID) {
        this.locationID = locationID;
    }

    public Integer getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Integer volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    @JsonProperty(value = "volume_total")
    public void setVolume_Total(Integer volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public Integer getVolumeRemain() {
        return volumeRemain;
    }

    public void setVolumeRemain(Integer volumeRemain) {
        this.volumeRemain = volumeRemain;
    }

    @JsonProperty(value = "volume_remain")
    public void setVolume_Remain(Integer volumeRemain) {
        this.volumeRemain = volumeRemain;
    }

    public Integer getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(Integer minVolume) {
        this.minVolume = minVolume;
    }

    @JsonProperty(value = "min_volume")
    public void setMin_Volume(Integer minVolume) {
        this.minVolume = minVolume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsByOrder() {
        return isByOrder;
    }

    public void setIsByOrder(Boolean isByOrder) {
        this.isByOrder = isByOrder;
    }

    @JsonProperty(value = "is_buy_order")
    public void setIs_By_Order(Boolean isByOrder) {
        this.isByOrder = isByOrder;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getIssued() {
        return issued;
    }

    public void setIssued(Timestamp issued) {
        this.issued = issued;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public int compareTo(Order o) {
        return this.getPrice().compareTo(o.getPrice());
    }
}
