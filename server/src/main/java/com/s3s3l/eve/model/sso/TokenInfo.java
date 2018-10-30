/** 
 * Project Name:eve-server 
 * File Name:TokenInfo.java 
 * Package Name:com.s3s3l.eve.model.sso 
 * Date:Sep 6, 20174:50:40 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.sso;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * </p>
 * ClassName:TokenInfo <br>
 * Date: Sep 6, 2017 4:50:40 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class TokenInfo {

    private String accessToken;
    private String tokenType;
    private Integer expiresIn;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    @JsonProperty("token_type")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("refresh_token")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
