/** 
 * Project Name:eve-server 
 * File Name:EnumCacheKeys.java 
 * Package Name:com.s3s3l.eve.model.enumetrations 
 * Date:Sep 8, 20174:42:39 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.enumetrations.sso;

/**
 * <p>
 * </p>
 * ClassName:EnumCacheKeys <br>
 * Date: Sep 8, 2017 4:42:39 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum EnumCacheKeys {
    TOKEN("token"), REFRESH_TOKEN("refresh_token");

    private String key;

    private EnumCacheKeys(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}
