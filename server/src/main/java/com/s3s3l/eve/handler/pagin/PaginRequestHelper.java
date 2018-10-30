/** 
 * Project Name:eve-server 
 * File Name:PaginRequestHelper.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Oct 12, 20171:26:19 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler.pagin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.http.HttpUtil.HttpResponse;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.utils.concurrent.TaskExecutor;

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
    private final TaskExecutor taskExecutor;

    public PaginRequestHelper(ESIConfiguration esiConfiguration, TaskExecutor taskExecutor) {
        this.esiConfiguration = esiConfiguration;
        this.taskExecutor = taskExecutor;
    }

    public <T> List<T> queryAll(PaginQuerier querier, Class<T> type) {
        List<T> result = new ArrayList<>();
        int currentPage = 1;
        HttpResponse<JsonNode> response = querier.query(currentPage);
        for (JsonNode node : (ArrayNode) response.getBody()) {
            result.add(json.treeToValue(node, type));
        }
        int totalPages = getXPage(response);
        List<Callable<Collection<T>>> calls = new ArrayList<>();
        while (currentPage++ < totalPages) {
            final int currentIndex = currentPage;
            calls.add(() -> {
                List<T> subResult = new ArrayList<>();
                HttpResponse<JsonNode> paginResponse = querier.query(currentIndex);
                for (JsonNode node : (ArrayNode) paginResponse.getBody()) {
                    subResult.add(json.treeToValue(node, type));
                }
                return subResult;
            });
        }

        result.addAll(taskExecutor.collectAll(calls));
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
