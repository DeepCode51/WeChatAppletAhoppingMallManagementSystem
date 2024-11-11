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
import com.yun.smart.mapper.DicInfoMapper;
import com.yun.smart.model.DicInfo;
import com.yun.smart.param.DicInfoAddParams;
import com.yun.smart.param.DicInfoDeleteParams;
import com.yun.smart.param.DicInfoSearchParams;
import com.yun.smart.param.DicInfoUpdateParams;
import com.yun.smart.service.DicInfoService;
import com.yun.smart.utils.AssertUtil;

/**
 * ServiceImpl - 字典表
 * @author qihh
 * @version 0.0.1
 */
@Service("dicInfoService")
public class DicInfoServiceImpl extends BaseServiceImpl<DicInfoMapper,DicInfo> implements DicInfoService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.DICINFO);

	@Resource
	private DicInfoMapper dicInfoMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(DicInfoSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		
		logger.info("DicInfoService-分页查询字典表入参:{}",params);
		List<Map<String,Object>> result = dicInfoMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<DicInfo> searchList(DicInfoSearchParams params) {
		if (null != params.getParentId() && params.getParentId().longValue() == 0L) {
			return dicInfoMapper.searchParents(params.toEntity());
		}
		
		return super.getList(params.toEntity());
	}

	@Override
	public DicInfo searchDetail(DicInfoSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(DicInfoAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(DicInfoUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(DicInfoDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(DicInfoDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		DicInfo dicInfo = null;
		List<DicInfo> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			dicInfo = new DicInfo();
			dicInfo.setId(id);
			dicInfo.setEnable(BooleanValue.FALSE.value());
			dicInfo.setUpdateTime(updateDate);
			dicInfo.setUpdateBy(userId);
			list.add(dicInfo);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public String getDicText(String dicValue, String dicName) {
		DicInfo dicInfo = new DicInfo();
		dicInfo.setDicName(dicName);
		dicInfo.setDicValue(dicValue);
		dicInfo = super.getDetail(dicInfo);
		AssertUtil.notNull(dicInfo, "字典："+ dicName +"不存在！");
		return dicInfo.getDicText();
	}
}

