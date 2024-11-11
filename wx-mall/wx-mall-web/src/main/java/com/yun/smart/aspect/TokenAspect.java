package com.yun.smart.aspect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yun.smart.base.TokenParams;
import com.yun.smart.consts.SessionConsts;
import com.yun.smart.service.AuthService;
import com.yun.smart.utils.WebUtils;

/**
 * @description 记录操作日志
 */
@Aspect
@Component
@Order(1)
public class TokenAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAspect.class);

    @Resource
	private AuthService authService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    
    }
    
    @Around("pointcut()")  
    public Object testHandleBody(ProceedingJoinPoint joinPoint) throws Throwable{
    	Object[] params = joinPoint.getArgs();
    	
    	HttpServletRequest request = WebUtils.getHttpServletRequest();
    	String authToken = request.getHeader(SessionConsts.AUTH_TOKEN_NAME);
    	if (null == authToken) {
    		return joinPoint.proceed(params);
    	}
    	
    	LOGGER.info("获取token:{}",authToken);
    	
    	/*
    	 * 约定第一个参数继承TokenParams类
    	 * 从请求头中获取token并赋值
    	 */
    	if (ArrayUtils.isNotEmpty(params)) {
    		TokenParams param;
			try {
				param = (TokenParams) params[0];
				param.setToken(authToken);
			} catch (Exception e) {
				LOGGER.error("赋值token失败：{}", e.getMessage());
			}
    	}
    	
    	return joinPoint.proceed(params);
    }

}
