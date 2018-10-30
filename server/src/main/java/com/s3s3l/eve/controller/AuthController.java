/** 
 * Project Name:eve-server 
 * File Name:AuthController.java 
 * Package Name:com.s3s3l.eve.controller 
 * Date:Aug 30, 20175:18:47 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.s3s3l.eve.service.SSOService;

/**
 * <p>
 * </p>
 * ClassName:AuthController <br>
 * Date: Aug 30, 2017 5:18:47 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SSOService ssoService;

    @RequestMapping(value = "/token", method = { RequestMethod.GET, RequestMethod.POST })
    public Object token() throws JsonProcessingException {
        return ssoService.getToken();
    }

    @RequestMapping(value = "/character", method = { RequestMethod.GET, RequestMethod.POST })
    public Object charactor() throws JsonProcessingException {
        return ssoService.getCharacterInfo();
    }
}
