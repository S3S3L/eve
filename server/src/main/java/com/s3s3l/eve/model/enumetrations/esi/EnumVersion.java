/** 
 * Project Name:eve-server 
 * File Name:EnumVersion.java 
 * Package Name:com.s3s3l.eve.model.enumetrations 
 * Date:Sep 29, 20176:51:37 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.enumetrations.esi;

/**
 * <p>
 * </p>
 * ClassName:EnumVersion <br>
 * Date: Sep 29, 2017 6:51:37 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum EnumVersion {
    LEGACY("lagacy"), LATEST("latest"), DEV("dev");

    private String info;

    private EnumVersion(String info) {
        this.info = info;
    }

    public String info() {
        return this.info;
    }
}
