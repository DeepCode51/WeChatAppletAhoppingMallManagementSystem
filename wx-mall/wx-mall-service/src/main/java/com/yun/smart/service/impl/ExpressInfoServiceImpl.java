package com.yun.smart.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.yun.smart.enums.BooleanValue;

import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.ExpressInfoMapper;
import com.yun.smart.model.ExpressInfo;
import com.yun.smart.param.ExpressInfoAddParams;
import com.yun.smart.param.ExpressInfoDeleteParams;
import com.yun.smart.param.ExpressInfoSearchParams;
import com.yun.smart.param.ExpressInfoUpdateParams;
import com.yun.smart.service.ExpressInfoService;

/**
 * ServiceImpl - 订单快递信息
 * @author qihh
 * @version 0.0.1
 */
@Service("expressInfoService")
public class ExpressInfoServiceImpl extends BaseServiceImpl<ExpressInfoMapper,ExpressInfo> implements ExpressInfoService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.EXPRESSINFO);

	@Resource
	private ExpressInfoMapper expressInfoMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(ExpressInfoSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		logger.info("ExpressInfoService-分页查询订单快递信息入参:{}",params);
		List<Map<String,Object>> result = expressInfoMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<ExpressInfo> searchList(ExpressInfoSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public ExpressInfo searchDetail(ExpressInfoSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(ExpressInfoAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(ExpressInfoUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(ExpressInfoDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(ExpressInfoDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		ExpressInfo expressInfo = null;
		List<ExpressInfo> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			expressInfo = new ExpressInfo();
			expressInfo.setId(id);
			expressInfo.setEnable(BooleanValue.FALSE.value());
			expressInfo.setUpdateTime(updateDate);
			expressInfo.setUpdateBy(userId);
			list.add(expressInfo);
		}
		
		super.updateBatchById(list);
	}
}

