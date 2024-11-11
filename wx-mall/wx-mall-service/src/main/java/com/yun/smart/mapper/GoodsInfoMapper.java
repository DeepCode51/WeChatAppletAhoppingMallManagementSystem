package com.yun.smart.mapper;

import java.util.List;
import java.util.Map;

import com.yun.smart.base.BaseMapper;
import com.yun.smart.base.BaseParams;
import com.yun.smart.model.GoodsInfo;

/**
 * Mapper - 产品信息
 * @author qihh
 * @version 0.0.1
 *
 */
public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {

	/**
	 * 查询结果集-不分页
	 * @param page
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> searchPage(BaseParams params);
	

}