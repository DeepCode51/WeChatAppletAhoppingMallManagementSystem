package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * dbModel - 订单快递信息
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2019-01-04 18:41:54
 */
@TableName("wx_express_info")
public class ExpressInfo extends BaseModel {
	
	private static final long serialVersionUID = 7258772620329928704L;
	
	/** 订单编号 */
	@TableField("order_no")
	private String orderNo;
	/** 快递公司 */
	@TableField("express_name")
	private String expressName;
	/** 快递公司代号 */
	@TableField("express_code")
	private String expressCode;
	/** 快递单号 */
	@TableField("express_no")
	private String expressNo;

	/** @return 订单编号 */
	public String getOrderNo() {
		return orderNo;
	}
	/** @param 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/** @return 快递公司 */
	public String getExpressName() {
		return expressName;
	}
	/** @param 快递公司 */
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	/** @return 快递公司代号 */
	public String getExpressCode() {
		return expressCode;
	}
	/** @param 快递公司代号 */
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	/** @return 快递单号 */
	public String getExpressNo() {
		return expressNo;
	}
	/** @param 快递单号 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}