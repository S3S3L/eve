/** 
 * Project Name:eve-server 
 * File Name:PlanetAttributes.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 30, 20171:49:34 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe.base;

/**
 * <p>
 * </p>
 * ClassName:PlanetAttributes <br>
 * Date: Sep 30, 2017 1:49:34 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PlanetAttributes {

    private Integer heightMap1;
    private Integer heightMap2;
    private Boolean population;
    private Integer shaderPreset;

    public Integer getHeightMap1() {
        return heightMap1;
    }

    public void setHeightMap1(Integer heightMap1) {
        this.heightMap1 = heightMap1;
    }

    public Integer getHeightMap2() {
        return heightMap2;
    }

    public void setHeightMap2(Integer heightMap2) {
        this.heightMap2 = heightMap2;
    }

    public Boolean getPopulation() {
        return population;
    }

    public void setPopulation(Boolean population) {
        this.population = population;
    }

    public Integer getShaderPreset() {
        return shaderPreset;
    }

    public void setShaderPreset(Integer shaderPreset) {
        this.shaderPreset = shaderPreset;
    }
}
