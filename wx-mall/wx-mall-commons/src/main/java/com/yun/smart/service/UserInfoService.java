package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.UserInfo;
import com.yun.smart.param.UserInfoAddParams;
import com.yun.smart.param.UserInfoDeleteParams;
import com.yun.smart.param.UserInfoSearchParams;
import com.yun.smart.param.UserInfoUpdateParams;

/**
 * Service - 用户
 * @author qihh
 * @version 0.0.1
 */
public interface UserInfoService extends BaseService<UserInfo> {

	/**
	 * 分页查询
	 * @param UserInfoSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(UserInfoSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param UserInfoSearchParams
	 * @return List<UserInfo>
	 */
	List<UserInfo> searchList(UserInfoSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param UserInfoSearchParams
	 * @return UserInfo
	 */
	UserInfo searchDetail(UserInfoSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param UserInfoAddParams
	 */
	void add(UserInfoAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param UserInfoUpdateParams
	 */
	void update(UserInfoUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param UserInfoDeleteParams
	 */
	void delete(UserInfoDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(UserInfoDeleteParams params);

	/**
	 * 查询用户基础信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getBaseInfo(UserInfoSearchParams params);

}
