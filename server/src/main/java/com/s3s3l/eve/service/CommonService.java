/** 
 * Project Name:eve-server 
 * File Name:CommonService.java 
 * Package Name:com.s3s3l.eve.service 
 * Date:Oct 13, 20176:46:19 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.service;

import java.util.List;

import javax.annotation.Nonnull;

import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Type;

/**
 * <p>
 * </p>
 * ClassName:CommonService <br>
 * Date: Oct 13, 2017 6:46:19 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface CommonService {
    
    void loadItems();
    
    void loadBluePrints();

    List<Type> getType(@Nonnull Type type);

    @Examine
    Type getTypeDetail(@Nonnull String typeID);

    List<Blueprint> getBlueprint(@Nonnull Blueprint blueprint);

    @Examine
    Blueprint getBlueprintDetail(@Nonnull String blueprintTypeID);
}
