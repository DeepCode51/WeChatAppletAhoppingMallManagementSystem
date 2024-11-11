package com.yun.smart.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.yun.smart.annotation.AccLog;
import com.yun.smart.builder.JsonResultBuilder;
import com.yun.smart.consts.SessionConsts;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.enums.OperateType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.model.UserInfo;
import com.yun.smart.param.UserInfoAddParams;
import com.yun.smart.param.UserInfoDeleteParams;
import com.yun.smart.param.UserInfoSearchParams;
import com.yun.smart.param.UserInfoUpdateParams;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 用户
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/userInfo")
@Api(value = "用户接口")
public class UserInfoController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.USERINFO);
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询用户", notes = "分页查询用户")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(UserInfoSearchParams params){
		logger.info("UserInfoController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = userInfoService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询用户列表", notes = "查询用户列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(UserInfoSearchParams params){
		logger.info("UserInfoController-查询列表入参:{}",params);
		List<UserInfo> result = userInfoService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询用户详情", notes = "查询用户详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(UserInfoSearchParams params){
		logger.info("UserInfoController-查询详情入参:{}",params);
		UserInfo result = userInfoService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建用户", notes = "新建用户")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(UserInfoAddParams params){
		logger.info("UserInfoController-新建入参:{}",params);
		userInfoService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新用户", notes = "更新用户")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(UserInfoUpdateParams params){
		logger.info("UserInfoController-更新入参:{}",params);
		userInfoService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(UserInfoDeleteParams params){
		logger.info("UserInfoController-删除入参:{}",params);
		userInfoService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除用户", notes = "批量删除用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(UserInfoDeleteParams params){
		logger.info("UserInfoController-批量删除入参:{}",Arrays.toString(params.getIds()));
		userInfoService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}

	@RequestMapping(value="/pc/v1/getBaseInfo",method=RequestMethod.POST)
	@ApiOperation(value = "查询用户详情", notes = "查询用户详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	@AccLog(operateName="查询个人登录信息",operateType=OperateType.SEARCH)
	public JsonResult getBaseInfo(UserInfoSearchParams params){
		logger.info("UserInfoController-查询用户基础信息入参:{}", params);
		Map<String,Object> result = userInfoService.getBaseInfo(params);
		return JsonResultBuilder.ok(result);
	}
	
}
