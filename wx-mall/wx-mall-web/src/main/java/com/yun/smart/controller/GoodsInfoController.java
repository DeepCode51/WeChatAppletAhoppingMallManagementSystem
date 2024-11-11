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
import com.yun.smart.model.GoodsInfo;
import com.yun.smart.param.GoodsInfoAddParams;
import com.yun.smart.param.GoodsInfoDeleteParams;
import com.yun.smart.param.GoodsInfoSearchParams;
import com.yun.smart.param.GoodsInfoUpdateParams;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.GoodsInfoService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 产品信息
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/goodsInfo")
@Api(value = "产品信息接口")
public class GoodsInfoController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.GOODSINFO);
	
	@Resource
	private GoodsInfoService goodsInfoService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询产品信息", notes = "分页查询产品信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(GoodsInfoSearchParams params){
		logger.info("GoodsInfoController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = goodsInfoService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询产品信息列表", notes = "查询产品信息列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(GoodsInfoSearchParams params){
		logger.info("GoodsInfoController-查询列表入参:{}",params);
		List<GoodsInfo> result = goodsInfoService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询产品信息详情", notes = "查询产品信息详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(GoodsInfoSearchParams params){
		logger.info("GoodsInfoController-查询详情入参:{}",params);
		GoodsInfo result = goodsInfoService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建产品信息", notes = "新建产品信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(GoodsInfoAddParams params){
		logger.info("GoodsInfoController-新建入参:{}",params);
		goodsInfoService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新产品信息", notes = "更新产品信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(GoodsInfoUpdateParams params){
		logger.info("GoodsInfoController-更新入参:{}",params);
		goodsInfoService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除产品信息", notes = "删除产品信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(GoodsInfoDeleteParams params){
		logger.info("GoodsInfoController-删除入参:{}",params);
		goodsInfoService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除产品信息", notes = "批量删除产品信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(GoodsInfoDeleteParams params){
		logger.info("GoodsInfoController-批量删除入参:{}",Arrays.toString(params.getIds()));
		goodsInfoService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/app/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询产品首页列表", notes = "查询产品首页列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchListApp(GoodsInfoSearchParams params){
		logger.info("GoodsInfoController-查询产品首页入参:{}",params);
		Map<String,Object> result = goodsInfoService.searchListApp(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/app/v1/searchCategory",method=RequestMethod.POST)
	@ApiOperation(value = "查询产品信息分类列表", notes = "查询产品信息分类列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchListCategory(GoodsInfoSearchParams params){
		logger.info("GoodsInfoController-查询产品信息分类列表入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getGoodsType(), "产品分类为空");
		List<Map<String,Object>> result = goodsInfoService.searchListCategory(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/app/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询产品信息详情", notes = "查询产品信息详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetailApp(GoodsInfoSearchParams params){
		logger.info("GoodsInfoController-查询详情入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getGoodsNo(), "商品编号为空");
		Map<String,Object> result = goodsInfoService.searchDetailApp(params);
		return JsonResultBuilder.ok(result);
	}
	
}
