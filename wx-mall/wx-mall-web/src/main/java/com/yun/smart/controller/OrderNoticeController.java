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

import com.yun.smart.model.OrderNotice;
import com.yun.smart.param.OrderNoticeAddParams;
import com.yun.smart.param.OrderNoticeDeleteParams;
import com.yun.smart.param.OrderNoticeSearchParams;
import com.yun.smart.param.OrderNoticeUpdateParams;
import com.yun.smart.service.OrderNoticeService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 消息
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/orderNotice")
@Api(value = "消息接口")
public class OrderNoticeController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ORDERNOTICE);
	
	@Resource
	private OrderNoticeService orderNoticeService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询消息", notes = "分页查询消息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(OrderNoticeSearchParams params){
		logger.info("OrderNoticeController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = orderNoticeService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询消息列表", notes = "查询消息列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(OrderNoticeSearchParams params){
		logger.info("OrderNoticeController-查询列表入参:{}",params);
		List<OrderNotice> result = orderNoticeService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询消息详情", notes = "查询消息详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(OrderNoticeSearchParams params){
		logger.info("OrderNoticeController-查询详情入参:{}",params);
		OrderNotice result = orderNoticeService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建消息", notes = "新建消息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(OrderNoticeAddParams params){
		logger.info("OrderNoticeController-新建入参:{}",params);
		orderNoticeService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新消息", notes = "更新消息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(OrderNoticeUpdateParams params){
		logger.info("OrderNoticeController-更新入参:{}",params);
		orderNoticeService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除消息", notes = "删除消息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(OrderNoticeDeleteParams params){
		logger.info("OrderNoticeController-删除入参:{}",params);
		orderNoticeService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除消息", notes = "批量删除消息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(OrderNoticeDeleteParams params){
		logger.info("OrderNoticeController-批量删除入参:{}",Arrays.toString(params.getIds()));
		orderNoticeService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/changeNoticeStatus",method=RequestMethod.POST)
	@ApiOperation(value = "设置消息已读", notes = "设置消息已读")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult changeNoticeStatus(OrderNoticeUpdateParams params){
		logger.info("OrderNoticeController-设置消息已读入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "消息ID为空");
		orderNoticeService.changeNoticeStatus(params);
		return JsonResultBuilder.ok();
	}
}
