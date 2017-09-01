/** 
 * Project Name:eve-server 
 * File Name:CharacterInfo.java 
 * Package Name:com.s3s3l.eve.model.sso 
 * Date:Sep 6, 20175:00:44 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.sso;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * </p>
 * ClassName:CharacterInfo <br>
 * Date: Sep 6, 2017 5:00:44 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class CharacterInfo {

    private Long characterID;
    private String characterName;
    private Timestamp ExpiresOn;
    private String scopes;
    private String tokenType;
    private String characterOwnerHash;
    private String intellectualProperty;

    public Long getCharacterID() {
        return characterID;
    }

    @JsonProperty("CharacterID")
    public void setCharacterID(Long characterID) {
        this.characterID = characterID;
    }

    public String getCharacterName() {
        return characterName;
    }

    @JsonProperty("CharacterName")
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Timestamp getExpiresOn() {
        return ExpiresOn;
    }

    @JsonProperty("ExpiresOn")
    public void setExpiresOn(Timestamp expiresOn) {
        ExpiresOn = expiresOn;
    }

    public String getScopes() {
        return scopes;
    }

    @JsonProperty("Scopes")
    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getTokenType() {
        return tokenType;
    }

    @JsonProperty("TokenType")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getCharacterOwnerHash() {
        return characterOwnerHash;
    }

    @JsonProperty("CharacterOwnerHash")
    public void setCharacterOwnerHash(String characterOwnerHash) {
        this.characterOwnerHash = characterOwnerHash;
    }

    public String getIntellectualProperty() {
        return intellectualProperty;
    }

    @JsonProperty("IntellectualProperty")
    public void setIntellectualProperty(String intellectualProperty) {
        this.intellectualProperty = intellectualProperty;
    }
}
