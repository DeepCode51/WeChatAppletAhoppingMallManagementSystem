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
import com.yun.smart.model.AddressList;
import com.yun.smart.param.AddressListAddParams;
import com.yun.smart.param.AddressListDeleteParams;
import com.yun.smart.param.AddressListSearchParams;
import com.yun.smart.param.AddressListUpdateParams;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.AddressListService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 地址
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/addressList")
@Api(value = "地址接口")
public class AddressListController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ADDRESSLIST);
	
	@Resource
	private AddressListService addressListService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询地址", notes = "分页查询地址")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(AddressListSearchParams params){
		logger.info("AddressListController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = addressListService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/app/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询个人地址列表", notes = "查询个人地址列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(AddressListSearchParams params){
		logger.info("AddressListController-查询个人地址列表入参:{}",params);
		List<AddressList> result = addressListService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value={"/pc/v1/searchDetail","/app/v1/searchDetail"},method=RequestMethod.POST)
	@ApiOperation(value = "查询地址详情", notes = "查询地址详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(AddressListSearchParams params){
		logger.info("AddressListController-查询详情入参:{}",params);
		AddressList result = addressListService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value={"/pc/v1/add","/app/v1/add"},method=RequestMethod.POST)
	@ApiOperation(value = "添加联系地址", notes = "添加联系地址")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(AddressListAddParams params){
		logger.info("AddressListController-添加联系地址入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getLinkMan(), "联系人为空");
		AssertUtil.notNull(params.getLinkPhone(), "联系电话为空");
		AssertUtil.notNull(params.getLinkAddr(), "联系地址为空");
		addressListService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value={"/pc/v1/update","/app/v1/update"},method=RequestMethod.POST)
	@ApiOperation(value = "编辑联系地址", notes = "编辑联系地址")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(AddressListUpdateParams params){
		logger.info("AddressListController-更新入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "联系地址ID为空");
		AssertUtil.notNull(params.getLinkMan(), "联系人为空");
		AssertUtil.notNull(params.getLinkPhone(), "联系电话为空");
		AssertUtil.notNull(params.getLinkAddr(), "联系地址为空");
		addressListService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value={"/pc/v1/delete","/app/v1/delete"},method=RequestMethod.POST)
	@ApiOperation(value = "删除地址", notes = "删除地址")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(AddressListDeleteParams params){
		logger.info("AddressListController-删除入参:{}",params);
		addressListService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value={"/pc/v1/deleteByIds","/app/v1/deleteByIds"},method=RequestMethod.POST)
	@ApiOperation(value = "批量删除地址", notes = "批量删除地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(AddressListDeleteParams params){
		logger.info("AddressListController-批量删除入参:{}",Arrays.toString(params.getIds()));
		addressListService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
}
