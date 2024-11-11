package com.yun.smart.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.consts.BillNoConsts;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.exception.BussinessException;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.OrderInfoMapper;
import com.yun.smart.model.ExpressInfo;
import com.yun.smart.model.FileImage;
import com.yun.smart.model.GoodsInfo;
import com.yun.smart.model.OrderComment;
import com.yun.smart.model.OrderInfo;
import com.yun.smart.param.OrderInfoAddParams;
import com.yun.smart.param.OrderInfoDeleteParams;
import com.yun.smart.param.OrderInfoSearchParams;
import com.yun.smart.param.OrderInfoSubmitParams;
import com.yun.smart.param.OrderInfoUpdateParams;
import com.yun.smart.service.AddressListService;
import com.yun.smart.service.ExpressInfoService;
import com.yun.smart.service.FileImageService;
import com.yun.smart.service.GoodsInfoService;
import com.yun.smart.service.OrderCommentService;
import com.yun.smart.service.OrderInfoService;
import com.yun.smart.utils.AssertUtil;
import com.yun.smart.utils.XMathUtil;

/**
 * ServiceImpl - 订单
 * @author qihh
 * @version 0.0.1
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfoMapper,OrderInfo> implements OrderInfoService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ORDERINFO);

	@Resource
	private OrderInfoMapper orderInfoMapper;
	@Resource
	private ExpressInfoService expressInfoService;
	@Resource
	private OrderCommentService orderCommentService;
	@Resource
	private GoodsInfoService goodsInfoService;
	@Resource
	private FileImageService fileImageService;
	@Resource
	private AddressListService addressListService;
	
	@Override
	public Page<Map<String,Object>> searchPage(OrderInfoSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		page.setOrderByField("a.create_time");
		
		logger.info("OrderInfoService-分页查询订单入参:{}",params);
		List<Map<String,Object>> result = orderInfoMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<OrderInfo> searchList(OrderInfoSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public OrderInfo searchDetail(OrderInfoSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void update(OrderInfoUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(OrderInfoDeleteParams params) {
		OrderInfo orderInfo = params.toEntity();
		orderInfo = this.getDetail(orderInfo);
		AssertUtil.notNull(orderInfo, "订单不存在或已删除。");
		
		if (OrderInfo.ORDER_STATUS_3.equals(orderInfo.getOrderStatus()) 
				|| OrderInfo.ORDER_STATUS_4.equals(orderInfo.getOrderStatus()) 
				|| OrderInfo.ORDER_STATUS_7.equals(orderInfo.getOrderStatus())) {
			throw new BussinessException("订单未完成，请勿删除。");
		}
		
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(OrderInfoDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		OrderInfo orderInfo = null;
		List<OrderInfo> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			orderInfo = new OrderInfo();
			orderInfo.setId(id);
			orderInfo.setEnable(BooleanValue.FALSE.value());
			orderInfo.setUpdateTime(updateDate);
			orderInfo.setUpdateBy(userId);
			list.add(orderInfo);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public List<Map<String, Object>> searchListApp(OrderInfoSearchParams params) {
		logger.info("OrderInfoService-查询订单列表入参:{}",params);
		Long userId = authService.getUserId(params.getToken());
		params.setUserId(userId);
		params.setEnable(BooleanValue.TRUE.value());
		List<Map<String,Object>> result = orderInfoMapper.searchPage(params);
		return result;
	}

	@Override
	public Map<String, Object> searchInfo(OrderInfoSearchParams params) {
		logger.info("OrderInfoService-查询订单详情入参:{}",params);
		// 查询订单
		Map<String, Object> orderInfo = orderInfoMapper.searchInfo(params.toEntity());
		AssertUtil.notNull(orderInfo, "订单不存在或已删除。");
		String orderNo = MapUtils.getString(orderInfo, "orderNo");
		
		// 查询订单物流
		ExpressInfo expressInfo = new ExpressInfo();
		expressInfo.setOrderNo(orderNo);
		expressInfo = expressInfoService.getDetail(expressInfo);
		
		// 查询评论
		OrderComment orderComment1 = new OrderComment();
		orderComment1.setOrderNo(orderNo);
		orderComment1.setCommentType(OrderComment.COMMENT_TYPE_1);
		orderComment1 = orderCommentService.getDetail(orderComment1);
		
		// 查询售后
		OrderComment orderComment2 = new OrderComment();
		orderComment2.setOrderNo(orderNo);
		orderComment2.setCommentType(OrderComment.COMMENT_TYPE_2);
		orderComment2 = orderCommentService.getDetail(orderComment2);
		
		// 查询图片(评论图，售后图)
		FileImage imageOne = new FileImage();
		imageOne.setBizNo(orderNo);
		List<FileImage> orderImages = fileImageService.getList(imageOne);
		
		Map<String, Object> result = new HashMap<>();
		result.put("orderInfo", orderInfo);
		result.put("orderExpress", expressInfo);
		result.put("orderComment1", orderComment1);
		result.put("orderComment2", orderComment2);
		result.put("orderImages", orderImages);
		return result;
	}

	@Override
	public void addOne(OrderInfoAddParams params) {
		logger.info("OrderInfoService-添加购物车入参:{}",params);
		// 查询商品状态
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsNo(params.getGoodsNo());
		goodsInfo = goodsInfoService.getDetail(goodsInfo);
		AssertUtil.notNull(goodsInfo, "产品不存在或已删除。");
	
		if (GoodsInfo.SELL_STATUS_2.equals(goodsInfo.getSellStatus())) {
			throw new BussinessException("该商品暂时无货，请您关注本店其他在售商品，谢谢。");
		}
		
		if (GoodsInfo.SELL_STATUS_3.equals(goodsInfo.getSellStatus())) {
			throw new BussinessException("该商品已下架，请您关注本店其他在售商品，谢谢。");
		}
		
		// 查询同一个用户， 同一件商品，购物车状态 是否已添加过，如已添加则数据累加。
		Long userId = authService.getUserId(params.getToken());
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setUserId(userId);
		orderInfo.setGoodsNo(params.getGoodsNo());
		orderInfo.setOrderStatus(OrderInfo.ORDER_STATUS_1);
		orderInfo = this.getDetail(orderInfo);
		if (null != orderInfo) {
			orderInfo.setGoodsNum(orderInfo.getGoodsNum() + params.getGoodsNum());
			orderInfo.setTotalPrice(XMathUtil.multiply(goodsInfo.getSellPrice(), new BigDecimal(orderInfo.getGoodsNum())));
			this.updateById(orderInfo);
		}
		else {
			orderInfo = params.toEntity();
			orderInfo.setOrderNo(BillNoConsts.ORDER_NO + IdWorker.getId());
			orderInfo.setOrderStatus(OrderInfo.ORDER_STATUS_1);
			orderInfo.setTotalPrice(XMathUtil.multiply(goodsInfo.getSellPrice(), new BigDecimal(params.getGoodsNum())));
			orderInfo.setUserId(userId);
			this.addModel(orderInfo,userId);
		}
	}

	@Override
	public void removeOne(OrderInfoAddParams params) {
		logger.info("OrderInfoService-从购物车移除产品入参:{}",params);
		// 查询订单
		OrderInfo orderInfo = params.toEntity();
		orderInfo.setOrderStatus(OrderInfo.ORDER_STATUS_1);
		orderInfo = this.getDetail(orderInfo);
		AssertUtil.notNull(orderInfo, "购物车不存在该订单或已被删除。");
		
		// 单价
		BigDecimal sellPrice = XMathUtil.divide(orderInfo.getTotalPrice(), new BigDecimal(orderInfo.getGoodsNum()));
		// 剩余数量
		Integer num = orderInfo.getGoodsNum() - 1;
		if (num < 1) {
			throw new BussinessException("购物车产品数量不能低于1件。");
		}
		
		orderInfo.setTotalPrice(XMathUtil.multiply(sellPrice, new BigDecimal(num)));
		orderInfo.setGoodsNum(num);
		this.updateById(orderInfo);
	}

	@Override
	public void submit(OrderInfoSubmitParams params) {
		logger.info("OrderInfoService-批量提交订单入参:{}",params);
		for (OrderInfoUpdateParams addParam : params.getOrderInfos()) {
			OrderInfo orderInfo = addParam.toEntity();
			orderInfo.setAddrId(params.getAddrId());
			if (OrderInfoSubmitParams.PAY_RESULT_1.equals(params.getPayResult())) {
				// 支付成功-状态改为待发货
				orderInfo.setOrderStatus(OrderInfo.ORDER_STATUS_3);
			} else {
				// 支付失败-状态改为待付款
				orderInfo.setOrderStatus(OrderInfo.ORDER_STATUS_2);
			}
			
			this.updateById(orderInfo);
		}
	}

	@Override
	public OrderInfo buyNow(OrderInfoAddParams params) {
		logger.info("OrderInfoService-立即购买产品入参:{}",params);
		
		// 查询商品状态
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsNo(params.getGoodsNo());
		goodsInfo = goodsInfoService.getDetail(goodsInfo);
		AssertUtil.notNull(goodsInfo, "产品不存在或已删除。");
	
		if (GoodsInfo.SELL_STATUS_2.equals(goodsInfo.getSellStatus())) {
			throw new BussinessException("该商品暂时无货，请您关注本店其他在售商品，谢谢。");
		}
		
		if (GoodsInfo.SELL_STATUS_3.equals(goodsInfo.getSellStatus())) {
			throw new BussinessException("该商品已下架，请您关注本店其他在售商品，谢谢。");
		}
		
		// 保存订单-购物车状态，实际点击付款后 - 未成功则改为待付款 - 成功改为待发货
		Long userId = authService.getUserId(params.getToken());
		OrderInfo orderInfo = params.toEntity();
		orderInfo.setOrderNo(BillNoConsts.ORDER_NO + IdWorker.getId());
		orderInfo.setOrderStatus(OrderInfo.ORDER_STATUS_2);
		orderInfo.setTotalPrice(XMathUtil.multiply(goodsInfo.getSellPrice(), new BigDecimal(params.getGoodsNum())));
		orderInfo.setUserId(userId);
		this.addModel(orderInfo,userId);
		
		return orderInfo;
	}

	@Override
	public List<Map<String,Object>> searchSubmit(OrderInfoSearchParams params) {
		logger.info("OrderInfoService-查询已提交待付款的订单列表入参:{}",params);
		
		// 查询已勾选提交的订单		
		Long userId = authService.getUserId(params.getToken());
		params.setUserId(userId);
		params.setEnable(BooleanValue.TRUE.value());
		List<Map<String,Object>> orderList = orderInfoMapper.searchPage(params);
		
		return orderList;
	}

	@Override
	public Map<String, Object> searchTotal(OrderInfoSearchParams params) {
		Long userId = authService.getUserId(params.getToken());
		params.setUserId(userId);
		params.setEnable(BooleanValue.TRUE.value());
		logger.info("OrderInfoService-查询个人订单各状态订单数量入参:{}",params);
		
		List<Map<String,Object>> total = orderInfoMapper.searchTotal(params);
		Map<String,Object> result = new HashMap<>();
		result.put("dfkTotal", 0);
		result.put("dfhTotal", 0);
		result.put("dshTotal", 0);
		result.put("thshTotal", 0);
		
		for (Map<String,Object> map : total) {
			String orderStatus = MapUtils.getString(map, "orderStatus");
			Integer num = MapUtils.getInteger(map, "total");
			switch (orderStatus) {
			case OrderInfo.ORDER_STATUS_2: result.replace("dfkTotal", num); break;
			case OrderInfo.ORDER_STATUS_3: result.replace("dfhTotal", num); break;
			case OrderInfo.ORDER_STATUS_4: result.replace("dshTotal", num); break;
			case OrderInfo.ORDER_STATUS_7: result.replace("thshTotal", num); break;
			}
		}
		
		return result;
	}
}

