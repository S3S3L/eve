/** 
 * Project Name:eve-server 
 * File Name:SSOConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Aug 30, 20175:40:06 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.configuration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.s3s3l.eve.model.enumetrations.EnumHttpMethod;

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

    private String endPoint;
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
        private Interface auth;
        private Interface token;
        private Interface refreshToken;
        private Interface obtainCharacter;

        public Interface getAuth() {
            return auth;
        }

        public void setAuth(Interface auth) {
            this.auth = auth;
        }

        public Interface getToken() {
            return token;
        }

        public void setToken(Interface token) {
            this.token = token;
        }

        public Interface getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(Interface refreshToken) {
            this.refreshToken = refreshToken;
        }

        public Interface getObtainCharacter() {
            return obtainCharacter;
        }

        public void setObtainCharacter(Interface obtainCharacter) {
            this.obtainCharacter = obtainCharacter;
        }
    }

    public static class Interface {
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
