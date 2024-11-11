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

import com.yun.smart.model.FileTips;
import com.yun.smart.param.FileTipsAddParams;
import com.yun.smart.param.FileTipsDeleteParams;
import com.yun.smart.param.FileTipsSearchParams;
import com.yun.smart.param.FileTipsUpdateParams;
import com.yun.smart.service.FileTipsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 文章
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/fileTips")
@Api(value = "文章接口")
public class FileTipsController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.FILETIPS);
	
	@Resource
	private FileTipsService fileTipsService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询文章", notes = "分页查询文章")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(FileTipsSearchParams params){
		logger.info("FileTipsController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = fileTipsService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询文章列表", notes = "查询文章列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(FileTipsSearchParams params){
		logger.info("FileTipsController-查询列表入参:{}",params);
		List<FileTips> result = fileTipsService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询文章详情", notes = "查询文章详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(FileTipsSearchParams params){
		logger.info("FileTipsController-查询详情入参:{}",params);
		FileTips result = fileTipsService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建文章", notes = "新建文章")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(FileTipsAddParams params){
		logger.info("FileTipsController-新建入参:{}",params);
		fileTipsService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新文章", notes = "更新文章")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(FileTipsUpdateParams params){
		logger.info("FileTipsController-更新入参:{}",params);
		fileTipsService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除文章", notes = "删除文章")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(FileTipsDeleteParams params){
		logger.info("FileTipsController-删除入参:{}",params);
		fileTipsService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除文章", notes = "批量删除文章")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(FileTipsDeleteParams params){
		logger.info("FileTipsController-批量删除入参:{}",Arrays.toString(params.getIds()));
		fileTipsService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
}
