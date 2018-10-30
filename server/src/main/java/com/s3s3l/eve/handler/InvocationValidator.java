/** 
 * Project Name:eve-server 
 * File Name:InvocationValidator.java 
 * Package Name:com.s3s3l.eve.handler 
 * Date:Oct 18, 201711:09:50 AM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.handler;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s3s3l.common.bean.verify.Examine;
import com.s3s3l.utils.reflect.ReflectionUtils;
import com.s3s3l.utils.verify.Verifier;

/**
 * <p>
 * </p>
 * ClassName:InvocationValidator <br>
 * Date: Oct 18, 2017 11:09:50 AM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Aspect
@Component
public class InvocationValidator {
    @Autowired
    private Verifier verifier;

    @Before("execution(* com.s3s3l.eve.service..*(..))")
    public void verify(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = joinPoint.getArgs();
        boolean verifyAll = ReflectionUtils.isAnnotationedWith(method, Examine.class);

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (verifyAll || parameterType.isAnnotationPresent(Examine.class)) {
                verifier.verify(args[i], parameterType);
            }
        }
    }
}
