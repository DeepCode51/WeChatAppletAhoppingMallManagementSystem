package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.OrderNotice;

/**
 * 删除消息请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
public class OrderNoticeDeleteParams extends TokenParams {
	
	private static final long serialVersionUID = 2075500241672506624L;
	
	/** id */
	private Long id;
	
	/** 业务IDs */
	private Long[] ids;

	/** @return id */
	public Long getId() {
		return id;
	}

	/** @param id */
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return OrderNotice
	 */
	public OrderNotice toEntity() {
		OrderNotice orderNotice = new OrderNotice();
		BeanUtils.copyProperties(this, orderNotice);
        
		return orderNotice;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}