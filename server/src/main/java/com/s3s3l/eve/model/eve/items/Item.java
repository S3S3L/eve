/** 
 * Project Name:eve-server 
 * File Name:Item.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 15, 20172:28:54 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.model.eve.items;  
/**
 * <p>
 * </p> 
 * ClassName:Item <br> 
 * Date:     Sep 15, 2017 2:28:54 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public interface Item extends Quantifiable{

    String getTypeID();

    void setTypeID(String typeID);
}
  