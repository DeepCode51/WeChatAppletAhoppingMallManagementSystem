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
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.model.OrderComment;
import com.yun.smart.param.OrderCommentAddParams;
import com.yun.smart.param.OrderCommentDeleteParams;
import com.yun.smart.param.OrderCommentResponseParams;
import com.yun.smart.param.OrderCommentSearchParams;
import com.yun.smart.param.OrderCommentUpdateParams;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.OrderCommentService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 评论
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/orderComment")
@Api(value = "评论接口")
public class OrderCommentController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ORDERCOMMENT);
	
	@Resource
	private OrderCommentService orderCommentService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询订单评论", notes = "分页查询订单评论")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(OrderCommentSearchParams params){
		logger.info("OrderCommentController-分页查询订单评论入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		Page<Map<String,Object>> result = orderCommentService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询评论列表", notes = "查询评论列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(OrderCommentSearchParams params){
		logger.info("OrderCommentController-查询列表入参:{}",params);
		List<OrderComment> result = orderCommentService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value={"/pc/v1/searchDetail","/app/v1/searchDetail"},method=RequestMethod.POST)
	@ApiOperation(value = "查询评论详情", notes = "查询评论详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(OrderCommentSearchParams params){
		logger.info("OrderCommentController-查询详情入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getOrderNo(), "订单编号为空");
		OrderComment result = orderCommentService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value={"/pc/v1/add","/app/v1/add"},method=RequestMethod.POST)
	@ApiOperation(value = "新建评论", notes = "新建评论")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(OrderCommentAddParams params){
		logger.info("OrderCommentController-新建入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getOrderNo(), "订单编号为空");
		AssertUtil.notNull(params.getGoodsStar(), "评分等级为空");
		AssertUtil.notNull(params.getContent(), "内容为空");
		orderCommentService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新评论", notes = "更新评论")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(OrderCommentUpdateParams params){
		logger.info("OrderCommentController-更新入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "评论ID为空");
		orderCommentService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除评论", notes = "删除评论")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(OrderCommentDeleteParams params){
		logger.info("OrderCommentController-删除入参:{}",params);
		orderCommentService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除评论", notes = "批量删除评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(OrderCommentDeleteParams params){
		logger.info("OrderCommentController-批量删除入参:{}",Arrays.toString(params.getIds()));
		orderCommentService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/searchInfo",method=RequestMethod.POST)
	@ApiOperation(value = "查询评论详情", notes = "查询评论详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchInfo(OrderCommentSearchParams params){
		logger.info("OrderCommentController-查询详情入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "评论ID为空");
		Map<String,Object> result = orderCommentService.searchInfo(params);
		return JsonResultBuilder.ok(result);
	}

	@RequestMapping(value="/pc/v1/batchResponse",method=RequestMethod.POST)
	@ApiOperation(value = "批量回复评论", notes = "批量回复评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult batchResponse(OrderCommentResponseParams params){
		logger.info("OrderCommentController-批量回复评论入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getIds(), "评论ID为空");
		AssertUtil.notNull(params.getResponseContent(), "回复内容为空");
		orderCommentService.batchResponse(params);
		return JsonResultBuilder.ok();
	}
	
}
