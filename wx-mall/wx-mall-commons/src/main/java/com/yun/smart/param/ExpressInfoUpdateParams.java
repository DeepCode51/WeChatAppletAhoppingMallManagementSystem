package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.ExpressInfo;

/**
 * 更新订单快递信息请求参数类 
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2019-01-04 18:41:54
 */
public class ExpressInfoUpdateParams extends TokenParams {
	
	private static final long serialVersionUID = -3412920445439157248L;
	
	/** id */
	private Long id;
	
	/** 订单编号 */
	private String orderNo;
	/** 快递公司 */
	private String expressName;
	/** 快递公司代号 */
	private String expressCode;
	/** 快递单号 */
	private String expressNo;

	/** @return id */
	public Long getId() {
		return id;
	}

	/** @param id */
	public void setId(Long id) {
		this.id = id;
	}

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
			
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return ExpressInfo
	 */
	public ExpressInfo toEntity() {
		ExpressInfo expressInfo = new ExpressInfo();
		BeanUtils.copyProperties(this, expressInfo);
		return expressInfo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}