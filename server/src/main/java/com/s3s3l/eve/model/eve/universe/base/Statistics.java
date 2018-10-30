/** 
 * Project Name:eve-server 
 * File Name:Statistics.java 
 * Package Name:com.s3s3l.eve.model.eve.universe.base 
 * Date:Sep 30, 20171:52:50 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p>
 * </p>
 * ClassName:Statistics <br>
 * Date: Sep 30, 2017 1:52:50 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class Statistics {
    /**
     * 密度
     */
    private Double density;
    private Double eccentricity;
    /**
     * 逃离速度
     */
    private Double escapeVelocity;
    /**
     * 是否支离破碎
     */
    private Boolean fragmented;
    /**
     * 寿命
     */
    private Double life;
    /**
     * 是否可以锁定
     */
    private Boolean locked;
    /**
     * 尘埃质量
     */
    private Double massDust;
    /**
     * 气体质量
     */
    private Double massGas;
    /**
     * 同步轨道周期
     */
    private Double orbitPeriod;
    /**
     * 同步轨道半径
     */
    private Double orbitRadius;
    /**
     * 压强
     */
    private Double pressure;
    /**
     * 半径
     */
    private Double radius;
    /**
     * 转速
     */
    private Double rotationRate;
    /**
     * 光谱类别
     */
    private String spectralClass;
    /**
     * 表面重力
     */
    private Double surfaceGravity;
    /**
     * 表面温度
     */
    private Double temperature;
    /**
     * 年龄
     */
    private Double age;
    /**
     * 亮度
     */
    private Double luminosity;

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(Double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public Double getEscapeVelocity() {
        return escapeVelocity;
    }

    public void setEscapeVelocity(Double escapeVelocity) {
        this.escapeVelocity = escapeVelocity;
    }

    public Boolean getFragmented() {
        return fragmented;
    }

    public void setFragmented(Boolean fragmented) {
        this.fragmented = fragmented;
    }

    public Double getLife() {
        return life;
    }

    public void setLife(Double life) {
        this.life = life;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Double getMassDust() {
        return massDust;
    }

    public void setMassDust(Double massDust) {
        this.massDust = massDust;
    }

    public Double getMassGas() {
        return massGas;
    }

    public void setMassGas(Double massGas) {
        this.massGas = massGas;
    }

    public Double getOrbitPeriod() {
        return orbitPeriod;
    }

    public void setOrbitPeriod(Double orbitPeriod) {
        this.orbitPeriod = orbitPeriod;
    }

    public Double getOrbitRadius() {
        return orbitRadius;
    }

    public void setOrbitRadius(Double orbitRadius) {
        this.orbitRadius = orbitRadius;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getRotationRate() {
        return rotationRate;
    }

    public void setRotationRate(Double rotationRate) {
        this.rotationRate = rotationRate;
    }

    public String getSpectralClass() {
        return spectralClass;
    }

    public void setSpectralClass(String spectralClass) {
        this.spectralClass = spectralClass;
    }

    public Double getSurfaceGravity() {
        return surfaceGravity;
    }

    public void setSurfaceGravity(Double surfaceGravity) {
        this.surfaceGravity = surfaceGravity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Double luminosity) {
        this.luminosity = luminosity;
    }
}
