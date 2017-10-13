/** 
 * Project Name:eve-server 
 * File Name:LogInterceptor.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Oct 12, 20177:27:38 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * <p>
 * </p>
 * ClassName:LogInterceptor <br>
 * Date: Oct 12, 2017 7:27:38 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class LogInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler == null) {
            return false;
        }
        
        ContentCachingRequestWrapper request = (ContentCachingRequestWrapper) req;

        logger.info("[Request] url: {}; request param: {}; request body: {}.", request.getRequestURI(),
                request.getParameterMap(), new String(request.getContentAsByteArray(),StandardCharsets.UTF_8));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
