/** 
 * Project Name:eve-server 
 * File Name:EnumGlobalizationType.java 
 * Package Name:com.s3s3l.eve.model.enumetrations.eve 
 * Date:Oct 11, 20171:33:13 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.enumetrations.eve;

/**
 * <p>
 * </p>
 * ClassName:EnumGlobalizationType <br>
 * Date: Oct 11, 2017 1:33:13 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum EnumGlobalizationType {
    Region(1, "星域"), Constellation(2, "星座"), SolarSystem(3, "星系"), Type(4, "物品"), Blueprint(5, "蓝图");

    private int value;
    private String info;

    private EnumGlobalizationType(int value, String info) {
        this.value = value;
        this.info = info;
    }

    public int value() {
        return this.value;
    }

    public String info() {
        return this.info;
    }

    public static EnumGlobalizationType parse(int value) {
        EnumGlobalizationType[] enums = values();

        for (EnumGlobalizationType currentEnum : enums) {
            if (currentEnum.value == value) {
                return currentEnum;
            }
        }
        throw new IllegalArgumentException(String.format("No matching constant for [%s]", value));
    }
}
