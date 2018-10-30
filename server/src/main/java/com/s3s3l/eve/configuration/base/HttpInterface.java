/** 
 * Project Name:eve-server 
 * File Name:HttpInterface.java 
 * Package Name:com.s3s3l.eve.configuration.base 
 * Date:Sep 29, 20173:53:29 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.configuration.base;

import org.apache.commons.lang3.StringUtils;

import com.s3s3l.eve.model.enumetrations.esi.EnumHttpMethod;

/**
 * <p>
 * </p> 
 * ClassName:HttpInterface <br> 
 * Date:     Sep 29, 2017 3:53:29 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public class HttpInterface {
    private String path;
    private EnumHttpMethod method;

    public String getUrl(String endPoint) {
        if (StringUtils.isEmpty(endPoint) || StringUtils.isEmpty(path)) {
            return endPoint;
        }
        if (endPoint.endsWith("/") && path.endsWith("/")) {
            return endPoint.concat(path.replaceFirst("/", ""));
        }
        if (!endPoint.endsWith("/") && !path.endsWith("/")) {
            return endPoint.concat("/").concat(path);
        }
        return endPoint.concat(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public EnumHttpMethod getMethod() {
        return method;
    }

    public void setMethod(EnumHttpMethod method) {
        this.method = method;
    }
}
  