/** 
 * Project Name:eve-server 
 * File Name:SSOServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Sep 6, 20174:49:21 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.s3s3l.eve.configuration.SSOConfiguration;
import com.s3s3l.eve.model.enumetrations.sso.EnumCacheKeys;
import com.s3s3l.eve.model.sso.CharacterInfo;
import com.s3s3l.eve.model.sso.TokenInfo;
import com.s3s3l.eve.service.SSOService;
import com.s3s3l.http.HttpException;
import com.s3s3l.http.HttpUtil;
import com.s3s3l.http.net.UrlUtil;
import com.s3s3l.utils.collection.MapBuilder;
import com.s3s3l.utils.string.StringUtil;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * <p>
 * </p>
 * ClassName:SSOServiceImpl <br>
 * Date: Sep 6, 2017 4:49:21 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class SSOServiceImpl implements SSOService {
    @Autowired
    private SSOConfiguration ssoConfiguration;
    @Autowired
    private HttpUtil http;
    @Autowired
    @Qualifier("expireCache")
    private Cache<String, String> expireCache;
    @Autowired
    private Cache<String, String> cache;

    @Override
    public String getToken() {
        String token = expireCache.getIfPresent(EnumCacheKeys.TOKEN.key());
        if (StringUtil.isEmpty(token)) {
            String refreshToken = cache.getIfPresent(EnumCacheKeys.REFRESH_TOKEN.key());
            if (StringUtil.isEmpty(refreshToken)) {
                TokenInfo tokenInfo = token(auth());
                expireCache.put(EnumCacheKeys.TOKEN.key(), tokenInfo.getAccessToken());
                cache.put(EnumCacheKeys.REFRESH_TOKEN.key(), tokenInfo.getRefreshToken());
                return tokenInfo.getAccessToken();
            }

            token = refreshToken(refreshToken).getAccessToken();
            expireCache.put(EnumCacheKeys.TOKEN.key(), token);
        }
        return token;
    }

    @Override
    public CharacterInfo getCharacterInfo() {
        return getCharacterInfo(getToken());
    }

    private String auth() {
        Map<String, String> params = new MapBuilder<>(new HashMap<String, String>())
                .put("ClientIdentifier", ssoConfiguration.getClientId())
                .put("RedirectUri", ssoConfiguration.getRedirectUri())
                .put("State", "zh-cn")
                .put("Scope", String.join(" ", ssoConfiguration.getScopes()))
                .put("ResponseType", "code")
                .put("__RequestVerificationToken", ssoConfiguration.getRequestVerificationToken())
                .put("CharacterId", ssoConfiguration.getCharacterId())
                .put("action", "授权")
                .build();

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, UrlUtil.buildRequestParam(params));
        Request request = new Request.Builder().url(ssoConfiguration.getInterfaces()
                .getAuth()
                .getUrl(ssoConfiguration.getEndpoint()))
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cookie",
                        "rating=pegi; optimizelyEndUserId=oeu1495194023281r0.8538152796312728; "
                                + "__utma=180277371.255602487.1494486971.1495428382.1495428382.1; "
                                + "__utmz=180277371.1495428382.1.1.utmcsr=google|utmccn=(organic)|"
                                + "utmcmd=organic|utmctr=(not%20provided); "
                                + ".ASPXAUTH=408044C7A268C80669F8E5B51187617C6AE765395DD497AE5F69CB"
                                + "BB329B63BDC85A5E7ECF539F2B401A158A0FC31B18E667AD005555B41CD7A93E"
                                + "CE2BB5AC28294472557977130379EBA577DE7158A6338065DDF4BF3BA4D7D058"
                                + "1C294174FEC7F8669C735D53CB72AE2143D812A8D9B77C216D; "
                                + "_gaexp=GAX1.2.cMzaRKH7TwicLMY1HmyoWQ.17452.0; "
                                + "optimizelySegments=%7B%22750310888%22%3A%22gc%22%2C%22750990793%"
                                + "22%3A%22referral%22%2C%22751131289%22%3A%22false%22%7D; "
                                + "optimizelyBuckets=%7B%7D; ai_user=BSgL+|2017-09-04T06:40:04.546Z; "
                                + "__RequestVerificationToken=9WsSEr7n6KKL9HnfmBLvGqehLMOyC_Iv8WWXJ_"
                                + "B-xQC6KuUbz0_o6dbY2-DTJ0HK4Qfjy46QRxKvp2AJscPQsUs4V8I1; "
                                + "cultureinfo=cultureinfo=en-us; _dc_gtm_UA-45583206-1=1; "
                                + "_gat_UA-45583206-1=1; ai_session=qh0IS|1504784832030|1504784850467.615; "
                                + "_ga=GA1.2.255602487.1494486971; _gid=GA1.2.1665413685.1504755409")
                .build();

        client.setFollowRedirects(false);
        String responseBody;
        try {
            Response response = client.newCall(request)
                    .execute();
            responseBody = response.body()
                    .string();
        } catch (IOException e) {
            throw new HttpException(e);
        }
        return responseBody.substring(responseBody.indexOf("code=") + 5, responseBody.indexOf("&amp;"));
    }

    private TokenInfo token(String authorizationCode) {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", ssoConfiguration.getAuthorization());
        Map<String, String> params = new MapBuilder<>(new HashMap<String, String>())
                .put("grant_type", "authorization_code")
                .put("code", authorizationCode)
                .build();
        return http.doPost(ssoConfiguration.getInterfaces()
                .getToken()
                .getUrl(ssoConfiguration.getEndpoint()), "application/x-www-form-urlencoded",
                ContentType.APPLICATION_FORM_URLENCODED, params, headers, TokenInfo.class);
    }

    private TokenInfo refreshToken(String refreshToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", ssoConfiguration.getAuthorization());
        Map<String, String> params = new MapBuilder<>(new HashMap<String, String>()).put("grant_type", "refresh_token")
                .put("refresh_token", refreshToken)
                .build();
        return http.doPost(ssoConfiguration.getInterfaces()
                .getRefreshToken()
                .getUrl(ssoConfiguration.getEndpoint()), "application/x-www-form-urlencoded",
                ContentType.APPLICATION_FORM_URLENCODED, params, headers, TokenInfo.class);
    }

    private CharacterInfo getCharacterInfo(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer ".concat(token));
        return http.doGet(ssoConfiguration.getInterfaces()
                .getObtainCharacter()
                .getUrl(ssoConfiguration.getEndpoint()), null, headers, CharacterInfo.class);
    }

}
