package com.yun.smart.mapper;

import java.util.List;
import java.util.Map;

import com.yun.smart.base.BaseMapper;
import com.yun.smart.base.BaseParams;
import com.yun.smart.model.OrderInfo;

/**
 * Mapper - 订单
 * @author qihh
 * @version 0.0.1
 *
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

	/**
	 * 查询订单列表-不分页
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> searchPage(BaseParams params);

	/**
	 * 查询订单明细
	 * @param orderInfo
	 * @return
	 */
	Map<String, Object> searchInfo(OrderInfo orderInfo);

	/**
	 * 查询个人订单各状态订单数量
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> searchTotal(BaseParams params);
	

}