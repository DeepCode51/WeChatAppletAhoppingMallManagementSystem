package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;
import java.math.BigDecimal;

/**
 * dbModel - 订单
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
@TableName("wx_order_info")
public class OrderInfo extends BaseModel {
	
	private static final long serialVersionUID = 759258952354011648L;
	
	/** 订单状态：1=购物车 */
	public static final String ORDER_STATUS_1 = "1";
	/** 订单状态：2=待付款 */
	public static final String ORDER_STATUS_2 = "2";
	/** 订单状态： 3=待发货 */
	public static final String ORDER_STATUS_3 = "3";
	/** 订单状态：4=待收货 */
	public static final String ORDER_STATUS_4 = "4";
	/** 订单状态：5=已取消 */
	public static final String ORDER_STATUS_5 = "5";
	/** 订单状态：6=已完成 */
	public static final String ORDER_STATUS_6 = "6";
	/** 订单状态：7=售后中 */
	public static final String ORDER_STATUS_7 = "7";
	
	/** 订单编号 */
	@TableField("order_no")
	private String orderNo;
	/** 用户ID */
	@TableField("user_id")
	private Long userId;
	/** 产品编号 */
	@TableField("goods_no")
	private String goodsNo;
	/** 应付款 */
	@TableField("total_price")
	private BigDecimal totalPrice;
	/** 购买数量 */
	@TableField("goods_num")
	private Integer goodsNum;
	/** 收货地址ID */
	@TableField("addr_id")
	private Long addrId;
	/** 订单状态：1=待付款 2=待发货 3=已发货 4=已取消 5=已完成 6=售后中 */
	@TableField("order_status")
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
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	/** @param 应付款 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}