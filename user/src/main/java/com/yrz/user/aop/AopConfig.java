package com.yrz.user.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yrz
 */
@Aspect
@Component
public class AopConfig {

    private final static Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(* com.yrz.user.controller.*Controller.*(..))")
    public void point(){ }

    @Before("point()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //url
        log.info("URL = {}",request.getRequestURL().toString());
        //传输类型
        log.info("HTTP_METHOD = {}",request.getMethod());
        //IP
        log.info("IP = {}" , request.getRemoteAddr());
        //类方法
        log.info("CLASS_METHOD = {}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        log.info("Args = {}",joinPoint.getArgs());
    }

}
