/** 
 * Project Name:eve-server 
 * File Name:CommonServiceImpl.java 
 * Package Name:com.s3s3l.eve.service.impl 
 * Date:Oct 13, 20176:47:32 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.eve.model.enumetrations.eve.EnumCacheScope;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Type;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.jdbc.exec.SqlExecutor;

/**
 * <p>
 * </p>
 * ClassName:CommonServiceImpl <br>
 * Date: Oct 13, 2017 6:47:32 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SqlExecutor sqlExecutor;
    @Autowired
    private CacheHelper<String, Object> cache;

    @Override
    public List<Type> getType(Type type) {
        return sqlExecutor.select(type, Type.class);
    }

    @Override
    public Type getTypeDetail(String typeID) {
        return (Type) cache.get(typeID, EnumCacheScope.Type.name());
    }

    @Override
    public List<Blueprint> getBlueprint(Blueprint blueprint) {
        return sqlExecutor.select(blueprint, Blueprint.class);
    }

    @Override
    public Blueprint getBlueprintDetail(String blueprintTypeID) {
        return (Blueprint) cache.get(blueprintTypeID, EnumCacheScope.Blueprint.name());
    }

}
