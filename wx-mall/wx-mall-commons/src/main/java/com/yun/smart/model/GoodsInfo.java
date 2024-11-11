package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;
import java.math.BigDecimal;

/**
 * dbModel - 产品信息
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:27
 */
@TableName("wx_goods_info")
public class GoodsInfo extends BaseModel {
	
	private static final long serialVersionUID = -3747185723902174208L;
	
	/** 状态：1=有货 */
	public static final String SELL_STATUS_1 = "1";
	/** 状态：2=无货 */
	public static final String SELL_STATUS_2 = "2";
	/** 状态：3=下架 */
	public static final String SELL_STATUS_3 = "3";
	/** 状态： 4=活动 */
	public static final String SELL_STATUS_4 = "4";
	
	/** 产品编号 */
	@TableField("goods_no")
	private String goodsNo;
	/** 商品名称 */
	@TableField("name")
	private String name;
	/** 进价 */
	@TableField("original_price")
	private BigDecimal originalPrice;
	/** 代理价 */
	@TableField("agent_price")
	private BigDecimal agentPrice;
	/** 售价 */
	@TableField("sell_price")
	private BigDecimal sellPrice;
	/** 活动价 */
	@TableField("discount_price")
	private BigDecimal discountPrice;
	/** 起售重量 */
	@TableField("weight")
	private BigDecimal weight;
	/** 重量单位：斤，kg，盒，包，袋，个 等等 */
	@TableField("weight_unit")
	private String weightUnit;
	/** 产地 */
	@TableField("grow_place")
	private String growPlace;
	/** 发货地 */
	@TableField("send_place")
	private String sendPlace;
	/** 发货时间：3天内，一周内等等 */
	@TableField("send_term")
	private String sendTerm;
	/** 发货说明：东三省不发货，北京快递费+3 等等 */
	@TableField("send_addition")
	private String sendAddition;
	/** 快递：顺丰，中通等等 */
	@TableField("express")
	private String express;
	/** 快递费 */
	@TableField("express_price")
	private BigDecimal expressPrice;
	/** 售前须知 */
	@TableField("before_sell")
	private String beforeSell;
	/** 售后须知 */
	@TableField("after_sell")
	private String afterSell;
	/** 状态：1=有货 2=无货 3=下架 4=活动 */
	@TableField("sell_status")
	private String sellStatus;
	/** 类型：1=水果 2=零食 3=用品 */
	@TableField("goods_type")
	private String goodsType;

	/** @return 产品编号 */
	public String getGoodsNo() {
		return goodsNo;
	}
	/** @param 产品编号 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	/** @return 商品名称 */
	public String getName() {
		return name;
	}
	/** @param 商品名称 */
	public void setName(String name) {
		this.name = name;
	}
	/** @return 进价 */
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	/** @param 进价 */
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	/** @return 代理价 */
	public BigDecimal getAgentPrice() {
		return agentPrice;
	}
	/** @param 代理价 */
	public void setAgentPrice(BigDecimal agentPrice) {
		this.agentPrice = agentPrice;
	}
	/** @return 售价 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	/** @param 售价 */
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	/** @return 活动价 */
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	/** @param 活动价 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	/** @return 起售重量 */
	public BigDecimal getWeight() {
		return weight;
	}
	/** @param 起售重量 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	/** @return 重量单位：斤，kg，盒，包，袋，个 等等 */
	public String getWeightUnit() {
		return weightUnit;
	}
	/** @param 重量单位：斤，kg，盒，包，袋，个 等等 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	/** @return 产地 */
	public String getGrowPlace() {
		return growPlace;
	}
	/** @param 产地 */
	public void setGrowPlace(String growPlace) {
		this.growPlace = growPlace;
	}
	/** @return 发货地 */
	public String getSendPlace() {
		return sendPlace;
	}
	/** @param 发货地 */
	public void setSendPlace(String sendPlace) {
		this.sendPlace = sendPlace;
	}
	/** @return 发货时间：3天内，一周内等等 */
	public String getSendTerm() {
		return sendTerm;
	}
	/** @param 发货时间：3天内，一周内等等 */
	public void setSendTerm(String sendTerm) {
		this.sendTerm = sendTerm;
	}
	/** @return 发货说明：东三省不发货，北京快递费+3 等等 */
	public String getSendAddition() {
		return sendAddition;
	}
	/** @param 发货说明：东三省不发货，北京快递费+3 等等 */
	public void setSendAddition(String sendAddition) {
		this.sendAddition = sendAddition;
	}
	/** @return 快递：顺丰，中通等等 */
	public String getExpress() {
		return express;
	}
	/** @param 快递：顺丰，中通等等 */
	public void setExpress(String express) {
		this.express = express;
	}
	/** @return 快递费 */
	public BigDecimal getExpressPrice() {
		return expressPrice;
	}
	/** @param 快递费 */
	public void setExpressPrice(BigDecimal expressPrice) {
		this.expressPrice = expressPrice;
	}
	/** @return 售前须知 */
	public String getBeforeSell() {
		return beforeSell;
	}
	/** @param 售前须知 */
	public void setBeforeSell(String beforeSell) {
		this.beforeSell = beforeSell;
	}
	/** @return 售后须知 */
	public String getAfterSell() {
		return afterSell;
	}
	/** @param 售后须知 */
	public void setAfterSell(String afterSell) {
		this.afterSell = afterSell;
	}
	/** @return 状态：1=有货 2=无货 3=下架 4=活动 */
	public String getSellStatus() {
		return sellStatus;
	}
	/** @param 状态：1=有货 2=无货 3=下架 4=活动 */
	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}
	/** @return 类型：1=水果 2=零食 3=用品 */
	public String getGoodsType() {
		return goodsType;
	}
	/** @param 类型：1=水果 2=零食 3=用品 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}