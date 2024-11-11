package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.ExpressInfo;
import com.yun.smart.param.ExpressInfoAddParams;
import com.yun.smart.param.ExpressInfoDeleteParams;
import com.yun.smart.param.ExpressInfoSearchParams;
import com.yun.smart.param.ExpressInfoUpdateParams;

/**
 * Service - 订单快递信息
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-20 14:55:07
 *
 */
public interface ExpressInfoService extends BaseService<ExpressInfo> {

	/**
	 * 分页查询
	 * @param ExpressInfoSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(ExpressInfoSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param ExpressInfoSearchParams
	 * @return List<ExpressInfo>
	 */
	List<ExpressInfo> searchList(ExpressInfoSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param ExpressInfoSearchParams
	 * @return ExpressInfo
	 */
	ExpressInfo searchDetail(ExpressInfoSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param ExpressInfoAddParams
	 */
	void add(ExpressInfoAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param ExpressInfoUpdateParams
	 */
	void update(ExpressInfoUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param ExpressInfoDeleteParams
	 */
	void delete(ExpressInfoDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(ExpressInfoDeleteParams params);

}
