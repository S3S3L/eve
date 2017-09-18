/** 
 * Project Name:eve-server 
 * File Name:Skill.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 15, 20173:57:20 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.model.eve;  
/**
 * <p>
 * </p> 
 * ClassName:Skill <br> 
 * Date:     Sep 15, 2017 3:57:20 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public class Skill extends AbstractItem {

    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
  