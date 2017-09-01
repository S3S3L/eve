/** 
 * Project Name:eve-server 
 * File Name:SSOConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Aug 30, 20175:40:06 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.configuration;

import com.s3s3l.app.component.annotation.Configuration;

/**
 * <p>
 * </p>
 * ClassName:SSOConfiguration <br>
 * Date: Aug 30, 2017 5:40:06 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Configuration(resources = { "file:config/sso.yml", "classpath:config/sso.yml" })
public class SSOConfiguration {

    private String endPoint;
    private String responseType;
    private String redirectUri;
    private String clientId;
    private String scope;
    private String state;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
