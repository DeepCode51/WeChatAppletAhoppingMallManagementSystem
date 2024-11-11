package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.DicInfo;
import com.yun.smart.param.DicInfoAddParams;
import com.yun.smart.param.DicInfoDeleteParams;
import com.yun.smart.param.DicInfoSearchParams;
import com.yun.smart.param.DicInfoUpdateParams;

/**
 * Service - 字典
 * @author qihh
 * @version 0.0.1
 */
public interface DicInfoService extends BaseService<DicInfo> {

	/**
	 * 分页查询
	 * @param DicInfoSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(DicInfoSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param DicInfoSearchParams
	 * @return List<DicInfo>
	 */
	List<DicInfo> searchList(DicInfoSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param DicInfoSearchParams
	 * @return DicInfo
	 */
	DicInfo searchDetail(DicInfoSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param DicInfoAddParams
	 */
	void add(DicInfoAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param DicInfoUpdateParams
	 */
	void update(DicInfoUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param DicInfoDeleteParams
	 */
	void delete(DicInfoDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(DicInfoDeleteParams params);

	/**
	 * 根据字典值查询字典词
	 * @param dicValue
	 * @return
	 */
	String getDicText(String dicValue, String dicName);

}
