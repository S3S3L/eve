/** 
 * Project Name:eve-server 
 * File Name:PaginRequestHelper.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Oct 12, 20171:26:19 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.http.HttpUtil.HttpResponse;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;

/**
 * <p>
 * </p>
 * ClassName:PaginRequestHelper <br>
 * Date: Oct 12, 2017 1:26:19 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PaginRequestHelper {

    private final JacksonHelper json = JacksonUtil.defaultHelper;
    private final ESIConfiguration esiConfiguration;

    public PaginRequestHelper(ESIConfiguration esiConfiguration) {
        this.esiConfiguration = esiConfiguration;
    }

    public <T> List<T> queryAll(PaginQuerier querier, Class<T> type) {
        List<T> result = new ArrayList<>();
        int currentPage = 1;
        HttpResponse<JsonNode> response = querier.query(currentPage);
        for (JsonNode node : (ArrayNode) response.getBody()) {
            result.add(json.treeToValue(node, type));
        }
        int totalPages = getXPage(response);
        while (currentPage++ < totalPages) {
            for (JsonNode node : (ArrayNode) response.getBody()) {
                result.add(json.treeToValue(node, type));
            }
        }

        return result;
    }

    private int getXPage(HttpResponse<?> response) {
        Map<String, List<String>> headers = response.getHeaders();
        if (!headers.containsKey(esiConfiguration.getTotalPageKey())) {
            return 0;
        }
        return Integer.valueOf(headers.get(esiConfiguration.getTotalPageKey())
                .get(0));
    }

}
