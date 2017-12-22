/** 
 * Project Name:eve-server 
 * File Name:GlobalizationQuerier.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Sep 30, 20171:02:31 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.handler.globalization;

import com.fasterxml.jackson.databind.JsonNode;
import com.s3s3l.eve.model.enumetrations.esi.EnumLanguage;
import com.s3s3l.http.HttpUtil.HttpResponse;

/**
 * <p>
 * </p> 
 * ClassName:GlobalizationQuerier <br> 
 * Date:     Sep 30, 2017 1:02:31 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public interface GlobalizationQuerier {

    HttpResponse<JsonNode> query(EnumLanguage lang);
}
  