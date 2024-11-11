package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.GoodsInfo;
import java.math.BigDecimal;

/**
 * 更新产品信息请求参数类 
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:27
 */
public class GoodsInfoUpdateParams extends TokenParams {
	
	private static final long serialVersionUID = -3575694981595974656L;
	
	/** id */
	private Long id;
	
	/** 产品编号 */
	private String goodsNo;
	/** 商品名称 */
	private String name;
	/** 进价 */
	private BigDecimal originalPrice;
	/** 代理价 */
	private BigDecimal agentPrice;
	/** 售价 */
	private BigDecimal sellPrice;
	/** 活动价 */
	private BigDecimal discountPrice;
	/** 起售重量 */
	private BigDecimal weight;
	/** 重量单位：斤，kg，盒，包，袋，个 等等 */
	private String weightUnit;
	/** 产地 */
	private String growPlace;
	/** 发货地 */
	private String sendPlace;
	/** 发货时间：3天内，一周内等等 */
	private String sendTerm;
	/** 发货说明：东三省不发货，北京快递费+3 等等 */
	private String sendAddition;
	/** 快递：顺丰，中通等等 */
	private String express;
	/** 快递费 */
	private BigDecimal expressPrice;
	/** 售前须知 */
	private String beforeSell;
	/** 售后须知 */
	private String afterSell;
	/** 状态：1=有货 2=无货 3=下架 4=活动 */
	private String sellStatus;
	/** 类型：1=水果 2=零食 3=用品 */
	private String goodsType;

	/** @return id */
	public Long getId() {
		return id;
	}

	/** @param id */
	public void setId(Long id) {
		this.id = id;
	}

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
			
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return GoodsInfo
	 */
	public GoodsInfo toEntity() {
		GoodsInfo goodsInfo = new GoodsInfo();
		BeanUtils.copyProperties(this, goodsInfo);
		return goodsInfo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}