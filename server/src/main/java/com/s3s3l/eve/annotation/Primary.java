/** 
 * Project Name:eve-server 
 * File Name:Primary.java 
 * Package Name:com.s3s3l.eve.annotation 
 * Date:Oct 9, 20175:08:32 PM 
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
 * ClassName:Primary <br> 
 * Date:     Oct 9, 2017 5:08:32 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Primary {

}
  