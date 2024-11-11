package com.yun.smart.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.yun.smart.builder.JsonResultBuilder;
import com.yun.smart.consts.SessionConsts;
import com.yun.smart.result.JsonResult;

import com.yun.smart.controller.BaseController;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;

import com.yun.smart.model.AccessLog;
import com.yun.smart.param.AccessLogAddParams;
import com.yun.smart.param.AccessLogDeleteParams;
import com.yun.smart.param.AccessLogSearchParams;
import com.yun.smart.param.AccessLogUpdateParams;
import com.yun.smart.service.AccessLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 访问日志
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/accessLog")
@Api(value = "访问日志接口")
public class AccessLogController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ACCESSLOG);
	
	@Resource
	private AccessLogService accessLogService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询访问日志", notes = "分页查询访问日志")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(AccessLogSearchParams params){
		logger.info("AccessLogController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = accessLogService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询访问日志列表", notes = "查询访问日志列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(AccessLogSearchParams params){
		logger.info("AccessLogController-查询列表入参:{}",params);
		List<AccessLog> result = accessLogService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询访问日志详情", notes = "查询访问日志详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(AccessLogSearchParams params){
		logger.info("AccessLogController-查询详情入参:{}",params);
		AccessLog result = accessLogService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建访问日志", notes = "新建访问日志")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(AccessLogAddParams params){
		logger.info("AccessLogController-新建入参:{}",params);
		accessLogService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新访问日志", notes = "更新访问日志")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(AccessLogUpdateParams params){
		logger.info("AccessLogController-更新入参:{}",params);
		accessLogService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除访问日志", notes = "删除访问日志")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(AccessLogDeleteParams params){
		logger.info("AccessLogController-删除入参:{}",params);
		accessLogService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除访问日志", notes = "批量删除访问日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(AccessLogDeleteParams params){
		logger.info("AccessLogController-批量删除入参:{}",Arrays.toString(params.getIds()));
		accessLogService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
}
