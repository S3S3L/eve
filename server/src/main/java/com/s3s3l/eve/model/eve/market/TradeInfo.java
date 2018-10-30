/** 
 * Project Name:eve-server 
 * File Name:TradeInfo.java 
 * Package Name:com.s3s3l.eve.model.eve.market 
 * Date:Oct 17, 20172:01:31 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.market;

import java.math.BigDecimal;

/**
 * <p>
 * </p>
 * ClassName:TradeInfo <br>
 * Date: Oct 17, 2017 2:01:31 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class TradeInfo {

    private BigDecimal highest;
    private BigDecimal lowest;

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(BigDecimal highest) {
        this.highest = highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(BigDecimal lowest) {
        this.lowest = lowest;
    }
}
