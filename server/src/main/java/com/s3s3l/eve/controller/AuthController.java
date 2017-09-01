/** 
 * Project Name:eve-server 
 * File Name:AuthController.java 
 * Package Name:com.s3s3l.eve.controller 
 * Date:Aug 30, 20175:18:47 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.controller;

import com.s3s3l.app.component.annotation.AutoInjected;
import com.s3s3l.eve.configuration.SSOConfiguration;
import com.s3s3l.web.bind.annotation.RequestMapping;
import com.s3s3l.web.bind.annotation.RestController;
import com.s3s3l.web.enumerations.HttpMethod;

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
    @AutoInjected
    private SSOConfiguration ssoConfiguration;

    @RequestMapping(url = "/callback", method = HttpMethod.GET)
    public String greeting() {
        return "hello,世界！".concat(ssoConfiguration.getEndPoint());
    }

}
