package com.yun.smart.param;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.yun.smart.base.PageParams;
import com.yun.smart.model.OrderInfo;
import com.yun.smart.utils.JsonUtils;

/**
 * 查询订单请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
public class OrderInfoSearchParams extends PageParams {

	private static final long serialVersionUID = -1667855945503832320L;

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
	
	/** 购买时间-最小值 */
	private Date minCreateTime; 
	
	/** 购买时间-最大值 */
	private Date maxCreateTime; 
	
	/** 购买人名称 */
	private String userName;
	
	/** 商品名称 */
	private String goodsName;
	
	/** 收件人名称 */
	private String linkMan;
	
	/** 订单编号集合 */
	private String[] orderNos;

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
	
	public Date getMinCreateTime() {
		return minCreateTime;
	}
	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}
	public Date getMaxCreateTime() {
		return maxCreateTime;
	}
	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String[] getOrderNos() {
		return orderNos;
	}
	public void setOrderNos(String[] orderNos) {
		this.orderNos = orderNos;
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