/** 
 * Project Name:eve-server 
 * File Name:Globalization.java 
 * Package Name:com.s3s3l.eve.annotation 
 * Date:Sep 30, 201711:28:46 AM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * </p>
 * ClassName:Globalization <br>
 * Date: Sep 30, 2017 11:28:46 AM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GlobalizationMap {

    /**
     * 
     * globalization for.
     * 
     * @return 
     * @since JDK 1.8
     */
    String value();
}
