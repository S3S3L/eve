/** 
 * Project Name:eve-server 
 * File Name:GlobalizationHelper.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Sep 30, 20171:01:07 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler.globalization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.s3s3l.common.bean.LinkedModel;
import com.s3s3l.eve.configuration.ESIConfiguration;
import com.s3s3l.eve.model.enumetrations.esi.EnumLanguage;
import com.s3s3l.eve.model.enumetrations.eve.EnumGlobalizationType;
import com.s3s3l.eve.model.eve.Globalization;
import com.s3s3l.eve.model.eve.GlobalizationTable;
import com.s3s3l.eve.model.result.GlobalizationResult;
import com.s3s3l.resource.JacksonHelper;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.utils.reflect.Reflection;
import com.s3s3l.utils.reflect.ReflectionUtils;
import com.s3s3l.utils.string.StringUtil;

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
            result.put(lang, json.treeToValue(querier.query(lang)
                    .getBody(), type));
        }

        return result.getGlobalizatedObject();
    }

    public <T> T queryData(GlobalizationQuerier querier, Class<T> type, Class<? extends LinkedModel<T>> responseType) {
        GlobalizationResult<T> result = new GlobalizationResult<>(type);
        for (EnumLanguage lang : esiConfiguration.getLanguages()) {
            result.put(lang, json.treeToValue(querier.query(lang)
                    .getBody(), responseType)
                    .convertTo());
        }

        return result.getGlobalizatedObject();
    }

    public <T>
            List<GlobalizationTable>
            buildGlobalizationTables(T model, Class<T> type, String id, EnumGlobalizationType gType) {
        List<GlobalizationTable> gTables = new ArrayList<>();
        Reflection<T> ref = Reflection.create(model);

        for (Field gField : ReflectionUtils.getFields(type, r -> Globalization.class.isAssignableFrom(r.getType()))) {
            GlobalizationTable gTable = new GlobalizationTable((Globalization) ref.getFieldValue(gField));
            gTable.setGuid(StringUtil.getUUIDNoLine());
            gTable.setId(id);
            gTable.setType(gType.value());
            gTable.setField(gField.getName());
            gTables.add(gTable);
        }

        return gTables;
    }
}
