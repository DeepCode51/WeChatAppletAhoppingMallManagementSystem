package com.yun.smart.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yun.smart.annotation.AccLog;
import com.yun.smart.base.TokenParams;
import com.yun.smart.consts.SessionConsts;
import com.yun.smart.model.AccessLog;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.AccessLogService;
import com.yun.smart.service.AuthService;
import com.yun.smart.token.UserToken;
import com.yun.smart.utils.NetUtils;
import com.yun.smart.utils.WebUtils;

/**
 * @description 记录操作日志
 */
@Aspect
@Component
@Order(2)
public class AccLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccLogAspect.class);

    /*
     * 用于记录线程时间
     */
    private ThreadLocal<Long> time = new ThreadLocal<Long>();
    
    @Resource
	private AccessLogService accessLogService;
    @Resource
	private AuthService authService;
    

    @Pointcut("@annotation(com.yun.smart.annotation.AccLog)")
    public void pointcut() {
    
    }

    /**
     * 方法开始前，记录开始时间
     */
    @Before("pointcut()")
    public void before() {
        /*
         * 记录开始时间
         */
        time.set(System.currentTimeMillis());
    }
    
    @Around("pointcut()")  
    public Object testHandleBody(ProceedingJoinPoint joinPoint) throws Throwable{
    	HttpServletRequest request = WebUtils.getHttpServletRequest();
    	String authToken = request.getHeader(SessionConsts.AUTH_TOKEN_NAME);
    	
    	/*
    	 * 约定第一个参数继承TokenParams类
		 * 从请求头中获取token并赋值
    	 */
    	Object[] params = joinPoint.getArgs();
    	if (ArrayUtils.isNotEmpty(params)) {
    		TokenParams param = (TokenParams) params[0];
    		param.setToken(authToken);
    	}
    	
    	return joinPoint.proceed(params);
    }

    /**
     * 方法结束后，记录操作日志
     *
     * @param joinPoint 切面点
     */
    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void after(JoinPoint joinPoint,JsonResult result) {
        
        try {
        	//耗时
        	long executeTime = System.currentTimeMillis() - time.get();
        	
        	// 日志注解
        	MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        	Method method = ms.getMethod();
        	AccLog accLog = method.getAnnotation(AccLog.class);
        	
        	HttpServletRequest request = WebUtils.getHttpServletRequest();
        	// IP
        	String ip = NetUtils.getIpAddress(request);
        	// 请求URL
        	String url = request.getServletPath().toString();
        	
        	// 请求方法
        	String operateMethod = ms.getDeclaringTypeName() + ms.getName();
        	
        	// 请求参数
        	String reqParam = null;
        	Object[] params = joinPoint.getArgs();
        	if (ArrayUtils.isNotEmpty(params)) {
        		reqParam = params[0].toString();
        	}
        	
        	// 响应结果
        	String resResult = null;
        	if (null != result) {
        		resResult = result.toString();
        	}
        	
        	String authToken = request.getHeader(SessionConsts.AUTH_TOKEN_NAME);
        	UserToken userInfo = authService.getUserToken(authToken);
        	if (null == userInfo) {
        		LOGGER.info("登录用户信息为空");
        		return;
        	}
        	
			AccessLog accessLog = new AccessLog();
			accessLog.setExecuteTime(executeTime);
			accessLog.setIp(ip);
			accessLog.setOperateMethod(operateMethod);
			accessLog.setOperateName(accLog.operateName());
			accessLog.setOperateType(accLog.operateType().getCode());
			accessLog.setReqParam(reqParam);
			accessLog.setResResult(resResult);
			accessLog.setUrl(url);
			accessLog.setUserId(userInfo.getUserId());
			accessLog.setUserName(userInfo.getUserName());
			accessLogService.addModel(accessLog, userInfo.getUserId());
			
		} catch (Exception e) {
			// 异常不处理，不影响业务方法
			LOGGER.error("保存操作日志失败", e);
		}
    }

}
