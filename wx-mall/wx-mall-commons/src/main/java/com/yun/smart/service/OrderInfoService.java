package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.yun.smart.base.BaseService;
import com.yun.smart.model.OrderInfo;
import com.yun.smart.param.OrderInfoAddParams;
import com.yun.smart.param.OrderInfoDeleteParams;
import com.yun.smart.param.OrderInfoSearchParams;
import com.yun.smart.param.OrderInfoSubmitParams;
import com.yun.smart.param.OrderInfoUpdateParams;

/**
 * Service - 订单
 * @author qihh
 * @version 0.0.1
 */
public interface OrderInfoService extends BaseService<OrderInfo> {

	/**
	 * 分页查询
	 * @param OrderInfoSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(OrderInfoSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param OrderInfoSearchParams
	 * @return List<OrderInfo>
	 */
	List<OrderInfo> searchList(OrderInfoSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param OrderInfoSearchParams
	 * @return OrderInfo
	 */
	OrderInfo searchDetail(OrderInfoSearchParams params);

	/**
	 * 修改数据-自定义参数
	 * @param OrderInfoUpdateParams
	 */
	void update(OrderInfoUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param OrderInfoDeleteParams
	 */
	void delete(OrderInfoDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(OrderInfoDeleteParams params);

	/**
	 * app-批量提交购物车订单
	 * @param params
	 */
	void submit(OrderInfoSubmitParams params);

	/**
	 * app-添加产品到购物车
	 * @param OrderInfoAddParams
	 */
	void addOne(OrderInfoAddParams params);

	/**
	 * app-从购物车移除产品
	 * @param params
	 */
	void removeOne(OrderInfoAddParams params);

	/**
	 * app-查询个人所有订单
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> searchListApp(OrderInfoSearchParams params);

	/**
	 * app-查询订单评论，物流，详情
	 * @param params
	 * @return
	 */
	Map<String, Object> searchInfo(OrderInfoSearchParams params);

	/**
	 * app-立即购买
	 * @param params
	 */
	OrderInfo buyNow(OrderInfoAddParams params);

	/**
	 * 查询已提交待付款的订单列表
	 * @param orderNos
	 * @return
	 */
	List<Map<String,Object>> searchSubmit(OrderInfoSearchParams params);

	/**
	 * 查询个人订单各状态订单数量
	 * @param params
	 * @return
	 */
	Map<String, Object> searchTotal(OrderInfoSearchParams params);

}
