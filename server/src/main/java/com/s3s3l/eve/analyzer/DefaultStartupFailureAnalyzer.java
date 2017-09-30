/** 
 * Project Name:common-utils 
 * File Name:DefaultStartupFailureAnalyzer.java 
 * Package Name:com.hellobike.tesla.utils.analyzer 
 * Date:Mar 30, 20175:10:10 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;

/**
 * <p>
 * </p>
 * ClassName:DefaultStartupFailureAnalyzer <br>
 * Date: Mar 30, 2017 5:10:10 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class DefaultStartupFailureAnalyzer implements FailureAnalyzer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public FailureAnalysis analyze(Throwable failure) {
        logger.error("Fail to startup application.",failure);
        return null;
    }

}
