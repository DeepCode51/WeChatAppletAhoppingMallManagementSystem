package com.yun.smart.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.model.FileTips;
import com.yun.smart.param.FileTipsDeleteParams;

import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.consts.DbConsts;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.FileTipsMapper;
import com.yun.smart.param.FileTipsAddParams;
import com.yun.smart.param.FileTipsDeleteParams;
import com.yun.smart.param.FileTipsSearchParams;
import com.yun.smart.param.FileTipsUpdateParams;
import com.yun.smart.service.FileTipsService;

/**
 * ServiceImpl - 文章
 * @author qihh
 * @version 0.0.1
 */
@Service("fileTipsService")
public class FileTipsServiceImpl extends BaseServiceImpl<FileTipsMapper,FileTips> implements FileTipsService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.FILETIPS);

	@Resource
	private FileTipsMapper fileTipsMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(FileTipsSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		
		logger.info("FileTipsService-分页查询文章入参:{}",params);
		List<Map<String,Object>> result = fileTipsMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<FileTips> searchList(FileTipsSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public FileTips searchDetail(FileTipsSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(FileTipsAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(FileTipsUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(FileTipsDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(FileTipsDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		FileTips fileTips = null;
		List<FileTips> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			fileTips = new FileTips();
			fileTips.setId(id);
			fileTips.setEnable(BooleanValue.FALSE.value());
			fileTips.setUpdateTime(updateDate);
			fileTips.setUpdateBy(userId);
			list.add(fileTips);
		}
		
		super.updateBatchById(list);
	}
}

