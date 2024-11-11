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

import com.yun.smart.model.ExpressInfo;
import com.yun.smart.param.ExpressInfoAddParams;
import com.yun.smart.param.ExpressInfoDeleteParams;
import com.yun.smart.param.ExpressInfoSearchParams;
import com.yun.smart.param.ExpressInfoUpdateParams;
import com.yun.smart.service.ExpressInfoService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 订单快递信息
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-20 14:55:07
 *
 */
@RestController
@RequestMapping("/smart/expressInfo")
@Api(value = "订单快递信息接口")
public class ExpressInfoController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.EXPRESSINFO);
	
	@Resource
	private ExpressInfoService expressInfoService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询订单快递信息", notes = "分页查询订单快递信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(ExpressInfoSearchParams params){
		logger.info("ExpressInfoController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = expressInfoService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询订单快递信息列表", notes = "查询订单快递信息列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(ExpressInfoSearchParams params){
		logger.info("ExpressInfoController-查询列表入参:{}",params);
		List<ExpressInfo> result = expressInfoService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value={"/pc/v1/searchDetail","/app/v1/searchDetail"},method=RequestMethod.POST)
	@ApiOperation(value = "查询订单快递信息详情", notes = "查询订单快递信息详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(ExpressInfoSearchParams params){
		logger.info("ExpressInfoController-查询详情入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getOrderNo(), "订单编号为空");
		ExpressInfo result = expressInfoService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建订单快递信息", notes = "新建订单快递信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(ExpressInfoAddParams params){
		logger.info("ExpressInfoController-新建入参:{}",params);
		expressInfoService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新订单快递信息", notes = "更新订单快递信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(ExpressInfoUpdateParams params){
		logger.info("ExpressInfoController-更新入参:{}",params);
		expressInfoService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除订单快递信息", notes = "删除订单快递信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(ExpressInfoDeleteParams params){
		logger.info("ExpressInfoController-删除入参:{}",params);
		expressInfoService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "删除测试订单快递信息", notes = "删除订单快递信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(ExpressInfoDeleteParams params){
		logger.info("ExpressInfoController-批量删除入参:{}",Arrays.toString(params.getIds()));
		expressInfoService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
}
