package com.yun.smart.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.exception.BussinessException;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.OrderCommentMapper;
import com.yun.smart.model.FileImage;
import com.yun.smart.model.OrderComment;
import com.yun.smart.model.OrderInfo;
import com.yun.smart.param.OrderCommentAddParams;
import com.yun.smart.param.OrderCommentDeleteParams;
import com.yun.smart.param.OrderCommentResponseParams;
import com.yun.smart.param.OrderCommentSearchParams;
import com.yun.smart.param.OrderCommentUpdateParams;
import com.yun.smart.service.FileImageService;
import com.yun.smart.service.OrderCommentService;
import com.yun.smart.service.OrderInfoService;
import com.yun.smart.utils.AssertUtil;

/**
 * ServiceImpl - 订单评论
 * @author qihh
 * @version 0.0.1
 */
@Service("orderCommentService")
public class OrderCommentServiceImpl extends BaseServiceImpl<OrderCommentMapper,OrderComment> implements OrderCommentService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ORDERCOMMENT);

	@Resource
	private OrderCommentMapper orderCommentMapper;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private FileImageService fileImageService;
	
	@Override
	public Page<Map<String,Object>> searchPage(OrderCommentSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		page.setOrderByField("a.create_time");
		
		logger.info("OrderCommentService-分页查询订单评论入参:{}",params);
		List<Map<String,Object>> result = orderCommentMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<OrderComment> searchList(OrderCommentSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public OrderComment searchDetail(OrderCommentSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(OrderCommentAddParams params) {
		logger.info("OrderCommentService-添加订单评论入参:{}",params);
		Long userId = authService.getUserId(params.getToken());
		// 查询订单
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderNo(params.getOrderNo());
		orderInfo = orderInfoService.getDetail(orderInfo);
		AssertUtil.notNull(orderInfo, "订单不存在或已删除。");
		
		// 同一订单只能评论一次
		OrderComment orderComment = new OrderComment();
		orderComment.setOrderNo(params.getOrderNo());
		orderComment = this.getDetail(orderComment);
		if (null != orderComment) {
			throw new BussinessException("您已对此次订单添加过评论。");
		}
		
		// 保存评论
		orderComment = params.toEntity();
		orderComment.setGoodsNo(orderInfo.getGoodsNo());
		orderComment.setUserId(userId);
		
		super.addModel(orderComment, userId);
	}

	@Override
	public void update(OrderCommentUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		if (StringUtils.isNotBlank(params.getResponseContent())) {
			params.setResponseTime(new Date());
		}
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(OrderCommentDeleteParams params) {
		logger.info("OrderCommentService-删除订单评论入参:{}",params);
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(OrderCommentDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		OrderComment orderComment = null;
		List<OrderComment> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			orderComment = new OrderComment();
			orderComment.setId(id);
			orderComment.setEnable(BooleanValue.FALSE.value());
			orderComment.setUpdateTime(updateDate);
			orderComment.setUpdateBy(userId);
			list.add(orderComment);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public Map<String, Object> searchInfo(OrderCommentSearchParams params) {
		logger.info("OrderCommentService-查询订单评论入参:{}",params);
		OrderComment orderComment = new OrderComment();
		orderComment.setId(params.getId());
		orderComment = this.getDetail(orderComment);
		AssertUtil.notNull(orderComment, "评论不存在或已删除。");
		
		// 查询图片(评论图，售后图)
		FileImage imageOne = new FileImage();
		imageOne.setBizNo(orderComment.getOrderNo());
		if (OrderComment.COMMENT_TYPE_1.equals(orderComment.getCommentType())) {
			imageOne.setBizType(FileImage.BIZ_TYPE_2);
		}
		if (OrderComment.COMMENT_TYPE_2.equals(orderComment.getCommentType())) {
			imageOne.setBizType(FileImage.BIZ_TYPE_3);
		}
		List<FileImage> orderImages = fileImageService.getList(imageOne);
		
		Map<String, Object> result = new HashMap<>();
		result.put("orderComment", orderComment);
		result.put("orderImages", orderImages);
		return result;
	}

	@Override
	public void batchResponse(OrderCommentResponseParams params) {
		logger.info("OrderCommentService-批量回复订单评论入参:{}",params);
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		OrderComment orderComment = null;
		List<OrderComment> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			orderComment = new OrderComment();
			orderComment.setId(id);
			orderComment.setResponseContent(params.getResponseContent());
			orderComment.setResponseTime(updateDate);
			orderComment.setUpdateTime(updateDate);
			orderComment.setUpdateBy(userId);
			list.add(orderComment);
		}
		
		super.updateBatchById(list);
		
	}
}

