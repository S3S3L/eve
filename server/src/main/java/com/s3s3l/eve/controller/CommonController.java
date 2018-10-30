/** 
 * Project Name:eve-server 
 * File Name:CommonController.java 
 * Package Name:com.s3s3l.eve.controller 
 * Date:Oct 16, 201711:12:34 AM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Type;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.resource.JsonHelper;

/**
 * <p>
 * </p>
 * ClassName:CommonController <br>
 * Date: Oct 16, 2017 11:12:34 AM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    private final JsonHelper json = JacksonUtil.defaultHelper;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/typeList", method = { RequestMethod.POST, RequestMethod.GET })
    public List<Type> typeList(@RequestBody(required = false) Type condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return commonService.getType(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, Type.class))
                        .orElse(new Type())));
    }

    @RequestMapping(value = "/type", method = { RequestMethod.POST, RequestMethod.GET })
    public Type type(@RequestBody(required = false) Type condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return commonService.getTypeDetail(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, Type.class))
                        .orElse(new Type()))
                .getTypeID());
    }

    @RequestMapping(value = "/blueprintList", method = { RequestMethod.POST, RequestMethod.GET })
    public List<Blueprint> blueprintList(@RequestBody(required = false) Blueprint condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return commonService.getBlueprint(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, Blueprint.class))
                        .orElse(new Blueprint())));
    }

    @RequestMapping(value = "/blueprint", method = { RequestMethod.POST, RequestMethod.GET })
    public Blueprint blueprint(@RequestBody(required = false) Blueprint condition,
            @RequestParam(required = false) Map<String, Object> paramCondition) {
        return commonService.getBlueprintDetail(Optional.ofNullable(condition)
                .orElse(Optional.ofNullable(paramCondition)
                        .map(r -> json.convert(r, Blueprint.class))
                        .orElse(new Blueprint()))
                .getBlueprintTypeID());
    }
}
