package com.yun.smart.param;

import java.util.List;

import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;

/**
 * 提交订单请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
public class OrderInfoSubmitParams extends TokenParams {

	private static final long serialVersionUID = -587298620396207999L;
	
	/** 付款结果：1=成功 */
	public static final String PAY_RESULT_1 = "1";
	/** 付款结果：2=失败 */
	public static final String PAY_RESULT_2 = "2";

	/** 订单集合 */
	private List<OrderInfoUpdateParams> orderInfos;
	
	/** 收货地址ID */
	private Long addrId;
	
	/** 付款结果：1=成功 2=失败 */
	private String payResult;

	public List<OrderInfoUpdateParams> getOrderInfos() {
		return orderInfos;
	}
	public void setOrderInfos(List<OrderInfoUpdateParams> orderInfos) {
		this.orderInfos = orderInfos;
	}
	/** @return 收货地址ID */
	public Long getAddrId() {
		return addrId;
	}
	/** @param 收货地址ID */
	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}
	public String getPayResult() {
		return payResult;
	}
	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}
	
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
}