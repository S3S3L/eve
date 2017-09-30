/** 
 * Project Name:eve-server 
 * File Name:AbstractItem.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 15, 20173:46:51 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.model.eve.items;  
/**
 * <p>
 * </p> 
 * ClassName:AbstractItem <br> 
 * Date:     Sep 15, 2017 3:46:51 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public abstract class AbstractItem implements Item {
    private int quantity;
    private int typeID;

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getTypeID() {
        return this.typeID;
    }

    @Override
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }
}
  