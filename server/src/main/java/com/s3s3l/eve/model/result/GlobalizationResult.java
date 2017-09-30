/** 
 * Project Name:eve-server 
 * File Name:GlobalizationResult.java 
 * Package Name:com.s3s3l.eve.model.result 
 * Date:Sep 30, 201711:03:49 AM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.result;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.s3s3l.eve.annotation.GlobalizationMap;
import com.s3s3l.eve.model.enumetrations.esi.EnumLanguage;
import com.s3s3l.eve.model.eve.Globalization;
import com.s3s3l.utils.reflect.Reflection;
import com.s3s3l.utils.reflect.ReflectionUtils;

/**
 * <p>
 * </p>
 * ClassName:GlobalizationResult <br>
 * Date: Sep 30, 2017 11:03:49 AM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class GlobalizationResult<T> {
    private final Map<EnumLanguage, T> data = new HashMap<>();
    private final Class<T> type;

    public GlobalizationResult(Class<T> type) {
        this.type = type;
    }

    public void put(EnumLanguage lang, T data) {
        this.data.put(lang, data);
    }

    public T get(EnumLanguage lang) {
        return this.data.get(lang);
    }

    public T getGlobalizatedObject() {
        if (data.isEmpty()) {
            return null;
        }

        List<Field> globalizationFields = ReflectionUtils.getFields(type)
                .stream()
                .filter(r -> r.isAnnotationPresent(GlobalizationMap.class)
                        && Globalization.class.isAssignableFrom(r.getType()))
                .collect(Collectors.toList());
        if (globalizationFields.isEmpty()) {
            return null;
        }

        Reflection<T> globalizationRef = null;

        for (Field field : globalizationFields) {
            String mapField = field.getAnnotation(GlobalizationMap.class)
                    .value();
            Globalization globalization = new Globalization();
            for (Entry<EnumLanguage, T> entry : data.entrySet()) {
                if (globalizationRef == null) {
                    globalizationRef = Reflection.create(entry.getValue());
                }
                EnumLanguage lang = entry.getKey();
                String localizationValue = Reflection.create(entry.getValue())
                        .getFieldValue(mapField)
                        .toString();
                switch (lang) {
                    case DE:
                        globalization.setDe(localizationValue);
                        break;
                    case EN_US:
                        globalization.setEn(localizationValue);
                        break;
                    case FR:
                        globalization.setFr(localizationValue);
                        break;
                    case JA:
                        globalization.setJa(localizationValue);
                        break;
                    case RU:
                        globalization.setRu(localizationValue);
                        break;
                    case ZH:
                        globalization.setZh(localizationValue);
                        break;
                    default:
                        break;
                }
            }
            try {
                globalizationRef.setFieldValue(field.getName(), globalization);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return globalizationRef.getObj();
    }
}
