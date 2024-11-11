package com.yun.smart.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.consts.DicInfoConsts;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.UserInfoMapper;
import com.yun.smart.model.UserInfo;
import com.yun.smart.param.UserInfoAddParams;
import com.yun.smart.param.UserInfoDeleteParams;
import com.yun.smart.param.UserInfoSearchParams;
import com.yun.smart.param.UserInfoUpdateParams;
import com.yun.smart.service.DicInfoService;
import com.yun.smart.service.OrderNoticeService;
import com.yun.smart.service.UserInfoService;
import com.yun.smart.utils.AssertUtil;

/**
 * ServiceImpl - 用户
 * @author qihh
 * @version 0.0.1
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper,UserInfo> implements UserInfoService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.USERINFO);

	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private OrderNoticeService orderNoticeService;
	@Resource
	private DicInfoService dicInfoService;
	
	@Override
	public Page<Map<String,Object>> searchPage(UserInfoSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		
		logger.info("UserInfoService-分页查询用户入参:{}",params);
		List<Map<String,Object>> result = userInfoMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<UserInfo> searchList(UserInfoSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public UserInfo searchDetail(UserInfoSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(UserInfoAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(UserInfoUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(UserInfoDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(UserInfoDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		UserInfo userInfo = null;
		List<UserInfo> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			userInfo = new UserInfo();
			userInfo.setId(id);
			userInfo.setEnable(BooleanValue.FALSE.value());
			userInfo.setUpdateTime(updateDate);
			userInfo.setUpdateBy(userId);
			list.add(userInfo);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public Map<String, Object> getBaseInfo(UserInfoSearchParams params) {
		Long userId = authService.getUserId(params.getToken());
		UserInfo userInfo = this.selectById(userId);
		AssertUtil.notNull(userInfo, "查询用户信息为空！");
		
		Map<String, Object> result = new HashMap<>();
		result.put("userRole", dicInfoService.getDicText(userInfo.getUserType(),DicInfoConsts.USER_TYPE));
		result.put("userName", userInfo.getUserName());
		result.put("userMsg", orderNoticeService.getNoReadNotice());
		return result;
	}
}

