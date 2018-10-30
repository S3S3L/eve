/** 
 * Project Name:eve-server 
 * File Name:EnumLanguage.java 
 * Package Name:com.s3s3l.eve.model.enumetrations 
 * Date:Sep 29, 20176:01:21 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.enumetrations.esi;

/**
 * <p>
 * </p>
 * ClassName:EnumLanguage <br>
 * Date: Sep 29, 2017 6:01:21 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum EnumLanguage {
    ZH("zh"), DE("de"), EN_US("en-us"), FR("fr"), JA("ja"), RU("ru");

    private String info;

    private EnumLanguage(String info) {
        this.info = info;
    }

    public String info() {
        return this.info;
    }
}
