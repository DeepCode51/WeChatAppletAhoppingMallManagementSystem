package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.OrderNotice;
import com.yun.smart.param.OrderNoticeAddParams;
import com.yun.smart.param.OrderNoticeDeleteParams;
import com.yun.smart.param.OrderNoticeSearchParams;
import com.yun.smart.param.OrderNoticeUpdateParams;

/**
 * Service - 消息
 * @author qihh
 * @version 0.0.1
 */
public interface OrderNoticeService extends BaseService<OrderNotice> {

	/**
	 * 分页查询
	 * @param OrderNoticeSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(OrderNoticeSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param OrderNoticeSearchParams
	 * @return List<OrderNotice>
	 */
	List<OrderNotice> searchList(OrderNoticeSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param OrderNoticeSearchParams
	 * @return OrderNotice
	 */
	OrderNotice searchDetail(OrderNoticeSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param OrderNoticeAddParams
	 */
	void add(OrderNoticeAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param OrderNoticeUpdateParams
	 */
	void update(OrderNoticeUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param OrderNoticeDeleteParams
	 */
	void delete(OrderNoticeDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(OrderNoticeDeleteParams params);

	/**
	 * 获取未读消息
	 * @return
	 */
	int getNoReadNotice();

	/**
	 * 设置消息已读
	 * @param params
	 */
	void changeNoticeStatus(OrderNoticeUpdateParams params);

}
