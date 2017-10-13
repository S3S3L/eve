/** 
 * Project Name:eve-server 
 * File Name:PaginQuerier.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Oct 12, 20171:27:45 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.s3s3l.http.HttpUtil.HttpResponse;

/**
 * <p>
 * </p>
 * ClassName:PaginQuerier <br>
 * Date: Oct 12, 2017 1:27:45 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface PaginQuerier {

    HttpResponse<JsonNode> query(int page);
}
