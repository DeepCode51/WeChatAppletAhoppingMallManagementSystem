package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.model.OrderInfo;
import java.math.BigDecimal;

/**
 * 新增订单请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
public class OrderInfoAddParams extends TokenParams {

	private static final long serialVersionUID = 1440161217888692480L;

	/** 订单编号 */
	private String orderNo;
	/** 用户ID */
	private Long userId;
	/** 产品编号 */
	private String goodsNo;
	/** 应付款 */
	private BigDecimal sellPrice;
	/** 购买数量 */
	private Integer goodsNum;
	/** 收货地址ID */
	private Long addrId;
	/** 订单状态：1=待付款 2=待发货 3=已发货 4=已取消 5=已完成 6=售后中 */
	private String orderStatus;

	/** @return 订单编号 */
	public String getOrderNo() {
		return orderNo;
	}
	/** @param 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/** @return 用户ID */
	public Long getUserId() {
		return userId;
	}
	/** @param 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/** @return 产品编号 */
	public String getGoodsNo() {
		return goodsNo;
	}
	/** @param 产品编号 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	/** @return 应付款 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	/** @param 应付款 */
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	/** @return 购买数量 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	/** @param 购买数量 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	/** @return 收货地址ID */
	public Long getAddrId() {
		return addrId;
	}
	/** @param 收货地址ID */
	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}
	/** @return 订单状态：1=待付款 2=待发货 3=已发货 4=已取消 5=已完成 6=售后中 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/** @param 订单状态：1=待付款 2=待发货 3=已发货 4=已取消 5=已完成 6=售后中 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return OrderInfo
	 */
	public OrderInfo toEntity() {
		OrderInfo orderInfo = new OrderInfo();
		BeanUtils.copyProperties(this, orderInfo);
		return orderInfo;
	}
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
}