/** 
 * Project Name:eve-server 
 * File Name:Astronomical.java 
 * Package Name:com.s3s3l.eve.model.eve.universe 
 * Date:Sep 29, 20178:30:33 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.universe.base;

/**
 * <p>
 * </p>
 * ClassName:Astronomical <br>
 * Date: Sep 29, 2017 8:30:33 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public abstract class Astronomical {

    private double[] max;
    private double[] min;
    private double[] center;

    public double[] getMax() {
        return max;
    }

    public void setMax(double[] max) {
        this.max = max;
    }

    public double[] getMin() {
        return min;
    }

    public void setMin(double[] min) {
        this.min = min;
    }

    public double[] getCenter() {
        return center;
    }

    public void setCenter(double[] center) {
        this.center = center;
    }
}
