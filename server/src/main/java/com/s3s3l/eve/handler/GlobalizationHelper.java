/** 
 * Project Name:eve-server 
 * File Name:GlobalizationHelper.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Sep 30, 20171:01:07 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler;

import com.s3s3l.common.bean.LinkedModel;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.model.enumetrations.esi.EnumLanguage;
import com.s3s3l.eve.model.result.GlobalizationResult;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;

/**
 * <p>
 * </p>
 * ClassName:GlobalizationHelper <br>
 * Date: Sep 30, 2017 1:01:07 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class GlobalizationHelper {

    private final JacksonHelper json = JacksonUtil.defaultHelper;
    private final ESIConfiguration esiConfiguration;

    public GlobalizationHelper(ESIConfiguration esiConfiguration) {
        this.esiConfiguration = esiConfiguration;
    }

    public <T> T queryData(GlobalizationQuerier querier, Class<T> type) {
        GlobalizationResult<T> result = new GlobalizationResult<>(type);
        for (EnumLanguage lang : esiConfiguration.getLanguages()) {
            result.put(lang, json.treeToValue(querier.query(lang), type));
        }

        return result.getGlobalizatedObject();
    }

    public <T> T queryData(GlobalizationQuerier querier, Class<T> type, Class<? extends LinkedModel<T>> responseType) {
        GlobalizationResult<T> result = new GlobalizationResult<>(type);
        for (EnumLanguage lang : esiConfiguration.getLanguages()) {
            result.put(lang, json.treeToValue(querier.query(lang), responseType).convertTo());
        }

        return result.getGlobalizatedObject();
    }
}
