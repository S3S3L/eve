/** 
 * Project Name:eve-server 
 * File Name:CrosFilter.java 
 * Package Name:com.s3s3l.eve.filter 
 * Date:Sep 7, 20174:05:40 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * <p>
 * </p>
 * ClassName:CrosFilter <br>
 * Date: Sep 7, 2017 4:05:40 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class CrosFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        ContentCachingResponseWrapper response = new ContentCachingResponseWrapper((HttpServletResponse) res);
        HttpServletRequest request = new ContentCachingRequestWrapper((HttpServletRequest) req);
        // 跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
        response.setHeader("Access-Control-Allow-Method", "*");

        chain.doFilter(request, response);
        response.copyBodyToResponse();
    }

    @Override
    public void destroy() {

    }

}
