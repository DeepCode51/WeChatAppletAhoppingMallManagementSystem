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

import com.yun.smart.model.DicInfo;
import com.yun.smart.param.DicInfoAddParams;
import com.yun.smart.param.DicInfoDeleteParams;
import com.yun.smart.param.DicInfoSearchParams;
import com.yun.smart.param.DicInfoUpdateParams;
import com.yun.smart.service.DicInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 字典
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/dicInfo")
@Api(value = "字典接口")
public class DicInfoController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.DICINFO);
	
	@Resource
	private DicInfoService dicInfoService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询字典", notes = "分页查询字典")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(DicInfoSearchParams params){
		logger.info("DicInfoController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = dicInfoService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询字典列表", notes = "查询字典列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(DicInfoSearchParams params){
		logger.info("DicInfoController-查询列表入参:{}",params);
		List<DicInfo> result = dicInfoService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询字典详情", notes = "查询字典详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(DicInfoSearchParams params){
		logger.info("DicInfoController-查询详情入参:{}",params);
		DicInfo result = dicInfoService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建字典", notes = "新建字典")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(DicInfoAddParams params){
		logger.info("DicInfoController-新建入参:{}",params);
		dicInfoService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新字典", notes = "更新字典")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(DicInfoUpdateParams params){
		logger.info("DicInfoController-更新入参:{}",params);
		dicInfoService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除字典", notes = "删除字典")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(DicInfoDeleteParams params){
		logger.info("DicInfoController-删除入参:{}",params);
		dicInfoService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除字典", notes = "批量删除字典")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(DicInfoDeleteParams params){
		logger.info("DicInfoController-批量删除入参:{}",Arrays.toString(params.getIds()));
		dicInfoService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
}
