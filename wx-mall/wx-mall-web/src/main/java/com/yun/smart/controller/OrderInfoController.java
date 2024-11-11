package com.yun.smart.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
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

import com.yun.smart.model.OrderInfo;
import com.yun.smart.param.OrderInfoAddParams;
import com.yun.smart.param.OrderInfoDeleteParams;
import com.yun.smart.param.OrderInfoSearchParams;
import com.yun.smart.param.OrderInfoSubmitParams;
import com.yun.smart.param.OrderInfoUpdateParams;
import com.yun.smart.service.OrderInfoService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 订单
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/orderInfo")
@Api(value = "订单接口")
public class OrderInfoController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ORDERINFO);
	
	@Resource
	private OrderInfoService orderInfoService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询订单", notes = "分页查询所有订单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(OrderInfoSearchParams params){
		logger.info("OrderInfoController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = orderInfoService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(OrderInfoSearchParams params){
		logger.info("OrderInfoController-查询列表入参:{}",params);
		List<OrderInfo> result = orderInfoService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询订单详情", notes = "查询订单详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(OrderInfoSearchParams params){
		logger.info("OrderInfoController-查询详情入参:{}",params);
		OrderInfo result = orderInfoService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value={"/pc/v1/update","/app/v1/update"},method=RequestMethod.POST)
	@ApiOperation(value = "更新订单", notes = "更新订单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(OrderInfoUpdateParams params){
		logger.info("OrderInfoController-更新入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "订单ID为空");
		AssertUtil.notNull(params.getOrderStatus(), "订单状态为空");
		orderInfoService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value={"/pc/v1/delete","/app/v1/delete"},method=RequestMethod.POST)
	@ApiOperation(value = "删除订单", notes = "删除订单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(OrderInfoDeleteParams params){
		logger.info("OrderInfoController-删除入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "订单ID为空");
		orderInfoService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value={"/pc/v1/deleteByIds","/app/v1/deleteByIds"},method=RequestMethod.POST)
	@ApiOperation(value = "批量删除订单", notes = "批量删除订单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(OrderInfoDeleteParams params){
		logger.info("OrderInfoController-批量删除入参:{}",Arrays.toString(params.getIds()));
		orderInfoService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
	
	@RequestMapping(value="/app/v1/addOne",method=RequestMethod.POST)
	@ApiOperation(value = "添加产品到购物车", notes = "添加产品到购物车")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult addOne(OrderInfoAddParams params){
		logger.info("OrderInfoController-添加产品到购物车入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getGoodsNo(), "商品编号为空");
		AssertUtil.notNull(params.getGoodsNum(), "商品数量为空");
		orderInfoService.addOne(params);
		return JsonResultBuilder.ok();
	}

	@RequestMapping(value="/app/v1/removeOne",method=RequestMethod.POST)
	@ApiOperation(value = "从购物车移除产品", notes = "从购物车移除产品")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult removeOne(OrderInfoAddParams params){
		logger.info("OrderInfoController-从购物车移除产品入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getOrderNo(), "订单编号为空");
		orderInfoService.removeOne(params);
		return JsonResultBuilder.ok();
	}

	@RequestMapping(value="/app/v1/submit",method=RequestMethod.POST)
	@ApiOperation(value = "批量提交购物车订单", notes = "批量提交购物车订单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult submit(@RequestBody OrderInfoSubmitParams params){
		logger.info("OrderInfoController-批量提交购物车订单入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getAddrId(), "收货地址为空");
		AssertUtil.notNull(params.getOrderInfos(), "订单数据为空");
		orderInfoService.submit(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/app/v1/buyNow",method=RequestMethod.POST)
	@ApiOperation(value = "立即购买", notes = "立即购买")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult buyNow(OrderInfoAddParams params){
		logger.info("OrderInfoController-立即购买入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getGoodsNo(), "产品编号为空");
		AssertUtil.notNull(params.getGoodsNum(), "产品数量为空");
		OrderInfo orderInfo = orderInfoService.buyNow(params);
		return JsonResultBuilder.ok(orderInfo);
	}

	@RequestMapping(value={"/pc/v1/searchInfo","/app/v1/searchInfo"},method=RequestMethod.POST)
	@ApiOperation(value = "查询订单详情", notes = "查询订单详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchInfo(OrderInfoSearchParams params){
		logger.info("OrderInfoController-查询详情入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getOrderNo(), "订单编号为空");
		Map<String,Object> result = orderInfoService.searchInfo(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/app/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询个人订单列表", notes = "查询个人订单列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchListApp(OrderInfoSearchParams params){
		logger.info("OrderInfoController-查询列表入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		List<Map<String,Object>> result = orderInfoService.searchListApp(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/app/v1/searchSubmit",method=RequestMethod.POST)
	@ApiOperation(value = "查询已提交待付款的订单列表", notes = "查询已提交待付款的订单列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchSubmit(OrderInfoSearchParams params){
		logger.info("OrderInfoController-查询已提交待付款的订单列表入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getOrderNos(), "订单编号为空");
		List<Map<String,Object>> result = orderInfoService.searchSubmit(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/app/v1/searchTotal",method=RequestMethod.POST)
	@ApiOperation(value = "查询个人订单状态统计数量", notes = "查询个人订单状态统计数量")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchTotal(OrderInfoSearchParams params){
		logger.info("OrderInfoController-查询个人订单状态统计数量入参:{}",params);
		Map<String,Object> result = orderInfoService.searchTotal(params);
		return JsonResultBuilder.ok(result);
	}
	
}
