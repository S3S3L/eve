/** 
 * Project Name:eve-server 
 * File Name:Product.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 15, 20173:46:02 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.model.eve.items;  
/**
 * <p>
 * </p> 
 * ClassName:Product <br> 
 * Date:     Sep 15, 2017 3:46:02 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public class Product extends AbstractItem {

    private Double probability;

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}
  