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
import com.yun.smart.mapper.OrderNoticeMapper;
import com.yun.smart.model.OrderNotice;
import com.yun.smart.param.OrderNoticeAddParams;
import com.yun.smart.param.OrderNoticeDeleteParams;
import com.yun.smart.param.OrderNoticeSearchParams;
import com.yun.smart.param.OrderNoticeUpdateParams;
import com.yun.smart.service.OrderNoticeService;

/**
 * ServiceImpl - 消息
 * @author qihh
 * @version 0.0.1
 */
@Service("orderNoticeService")
public class OrderNoticeServiceImpl extends BaseServiceImpl<OrderNoticeMapper,OrderNotice> implements OrderNoticeService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ORDERNOTICE);

	@Resource
	private OrderNoticeMapper orderNoticeMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(OrderNoticeSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		
		logger.info("OrderNoticeService-分页查询消息入参:{}",params);
		List<Map<String,Object>> result = orderNoticeMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<OrderNotice> searchList(OrderNoticeSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public OrderNotice searchDetail(OrderNoticeSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(OrderNoticeAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(OrderNoticeUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(OrderNoticeDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(OrderNoticeDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		OrderNotice orderNotice = null;
		List<OrderNotice> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			orderNotice = new OrderNotice();
			orderNotice.setId(id);
			orderNotice.setEnable(BooleanValue.FALSE.value());
			orderNotice.setUpdateTime(updateDate);
			orderNotice.setUpdateBy(userId);
			list.add(orderNotice);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public int getNoReadNotice() {
		OrderNotice notice = new OrderNotice();
		notice.setNoticeStatus(OrderNotice.NOTICE_STATUS_1);
		List<OrderNotice> count = super.getList(notice);
		return count.size();
	}

	@Override
	public void changeNoticeStatus(OrderNoticeUpdateParams params) {
		logger.info("OrderNoticeService-设置消息已读入参:{}",params);
		OrderNotice orderNotice = params.toEntity();
		orderNotice.setNoticeStatus(OrderNotice.NOTICE_STATUS_2);
		this.updateById(orderNotice);
	}
}

