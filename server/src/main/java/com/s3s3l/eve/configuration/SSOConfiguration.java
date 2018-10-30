/** 
 * Project Name:eve-server 
 * File Name:SSOConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Aug 30, 20175:40:06 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.s3s3l.eve.configuration.base.HttpInterface;

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
@Configuration
@ConfigurationProperties(prefix = "sso")
public class SSOConfiguration {

    private String endpoint;
    private String responseType;
    private String redirectUri;
    private String clientId;
    private String secretKey;
    private String state;
    private Interfaces interfaces;
    private List<String> scopes;
    private String requestVerificationToken;
    private String characterId;
    private String authorization;

    public static class Interfaces {
        private HttpInterface auth;
        private HttpInterface token;
        private HttpInterface refreshToken;
        private HttpInterface obtainCharacter;

        public HttpInterface getAuth() {
            return auth;
        }

        public void setAuth(HttpInterface auth) {
            this.auth = auth;
        }

        public HttpInterface getToken() {
            return token;
        }

        public void setToken(HttpInterface token) {
            this.token = token;
        }

        public HttpInterface getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(HttpInterface refreshToken) {
            this.refreshToken = refreshToken;
        }

        public HttpInterface getObtainCharacter() {
            return obtainCharacter;
        }

        public void setObtainCharacter(HttpInterface obtainCharacter) {
            this.obtainCharacter = obtainCharacter;
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Interfaces getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Interfaces interfaces) {
        this.interfaces = interfaces;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getRequestVerificationToken() {
        return requestVerificationToken;
    }

    public void setRequestVerificationToken(String requestVerificationToken) {
        this.requestVerificationToken = requestVerificationToken;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
