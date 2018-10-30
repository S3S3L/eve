/** 
 * Project Name:eve-server 
 * File Name:SSOService.java 
 * Package Name:com.s3s3l.eve.service 
 * Date:Sep 6, 20174:48:53 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/  
  
package com.s3s3l.eve.service;

import com.s3s3l.eve.model.sso.CharacterInfo;

/**
 * <p>
 * </p> 
 * ClassName:SSOService <br> 
 * Date:     Sep 6, 2017 4:48:53 PM <br>
 *  
 * @author   kehw_zwei 
 * @version  1.0.0
 * @since    JDK 1.8
 */
public interface SSOService {
    
    String getToken();
    
    CharacterInfo getCharacterInfo();
}
  