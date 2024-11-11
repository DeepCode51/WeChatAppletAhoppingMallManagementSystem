package com.yun.smart.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yun.smart.annotation.AccLog;
import com.yun.smart.builder.JsonResultBuilder;
import com.yun.smart.consts.SessionConsts;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.enums.OperateType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.param.AuthParams;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.AuthService;
import com.yun.smart.token.UserLoginParams;
import com.yun.smart.token.WeixinLoginParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 登录鉴权
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/auth")
@Api(value = "用户鉴权")
public class AuthController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.AUTH);
	
	@Resource
	private AuthService authService;
	
	@RequestMapping(value="/pc/v1/login",method=RequestMethod.POST)
	@ApiOperation(value = "登录", notes = "登录")
	public JsonResult login(UserLoginParams params){
		logger.info("AuthController-登录:{}", params);
		String authToken = authService.login(params);
		return JsonResultBuilder.ok(SessionConsts.AUTH_TOKEN_KEY, authToken);
	}
	
	@RequestMapping(value="/app/v1/login",method=RequestMethod.POST)
	@ApiOperation(value = "登录", notes = "登录")
	public JsonResult weAppLogin(WeixinLoginParams params){
		logger.info("AuthController-登录:{}", params);
		String authToken = authService.weAppLogin(params);
		return JsonResultBuilder.ok(SessionConsts.AUTH_TOKEN_KEY, authToken);
	}
	
	@RequestMapping(value="/pc/v1/getUserToken",method=RequestMethod.GET)
	@ApiOperation(value = "获取登录信息", notes = "获取登录信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	@AccLog(operateName="获取登录信息",operateType=OperateType.SEARCH)
	public JsonResult getUserToken(AuthParams params){
		logger.info("AuthController-获取登录信息");
		Map<String, Object> userInfo = authService.getUserContext(params.getToken());
		return JsonResultBuilder.ok(userInfo);
	}
	
}
