/** 
 * Project Name:eve-server 
 * File Name:Application.java 
 * Package Name:com.s3s3l.eve.app 
 * Date:Aug 30, 20175:04:33 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.app;

import com.s3s3l.app.component.context.ComponentConfigurationContext;
import com.s3s3l.web.app.DefaultWebApplication;
import com.s3s3l.web.app.annotation.ScanPackages;
import com.s3s3l.web.filter.CharacterEncodingFilter;

/**
 * <p>
 * </p>
 * ClassName:Application <br>
 * Date: Aug 30, 2017 5:04:33 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@ScanPackages("com.s3s3l.eve")
public class Application {

    public static ComponentConfigurationContext ctx;

    public static void main(String[] args) throws Throwable {
        DefaultWebApplication app = new DefaultWebApplication(Application.class);
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        app.addFilter(filter, "/*");
        ctx = app.run();
    }
}
