/** 
 * Project Name:eve-server 
 * File Name:EnumHttpMethod.java 
 * Package Name:com.s3s3l.eve.model.enumetrations 
 * Date:Sep 4, 20174:52:10 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.enumetrations.esi;

/**
 * <p>
 * </p>
 * ClassName:EnumHttpMethod <br>
 * Date: Sep 4, 2017 4:52:10 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum EnumHttpMethod {
    GET(1, "GET"), POST(2, "POST");

    private int value;
    private String info;

    private EnumHttpMethod(int value, String info) {
        this.value = value;
        this.info = info;
    }

    public int value() {
        return this.value;
    }

    public String info() {
        return this.info;
    }

    public static EnumHttpMethod parse(int value) {
        EnumHttpMethod[] enums = values();

        for (EnumHttpMethod currentEnum : enums) {
            if (currentEnum.value == value) {
                return currentEnum;
            }
        }
        throw new IllegalArgumentException(String.format("No matching constant for [%s]", value));
    }
}
