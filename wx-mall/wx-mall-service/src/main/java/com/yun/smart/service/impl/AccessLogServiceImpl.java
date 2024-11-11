package com.yun.smart.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.AccessLogMapper;
import com.yun.smart.model.AccessLog;
import com.yun.smart.param.AccessLogAddParams;
import com.yun.smart.param.AccessLogDeleteParams;
import com.yun.smart.param.AccessLogSearchParams;
import com.yun.smart.param.AccessLogUpdateParams;
import com.yun.smart.service.AccessLogService;

/**
 * ServiceImpl - 访问日志
 * @author qihh
 * @version 0.0.1
 */
@Service("accessLogService")
public class AccessLogServiceImpl extends BaseServiceImpl<AccessLogMapper,AccessLog> implements AccessLogService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ACCESSLOG);

	@Resource
	private AccessLogMapper accessLogMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(AccessLogSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		
		logger.info("AccessLogService-分页查询访问日志入参:{}",params);
		List<Map<String,Object>> result = accessLogMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<AccessLog> searchList(AccessLogSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public AccessLog searchDetail(AccessLogSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(AccessLogAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(AccessLogUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(AccessLogDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(AccessLogDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		AccessLog accessLog = null;
		List<AccessLog> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			accessLog = new AccessLog();
			accessLog.setId(id);
			accessLog.setEnable(BooleanValue.FALSE.value());
			accessLog.setUpdateTime(updateDate);
			accessLog.setUpdateBy(userId);
			list.add(accessLog);
		}
		
		super.updateBatchById(list);
	}
}

