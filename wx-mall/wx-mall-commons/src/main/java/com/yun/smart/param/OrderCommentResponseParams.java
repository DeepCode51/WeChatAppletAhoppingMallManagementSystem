package com.yun.smart.param;

import org.springframework.beans.BeanUtils;

import com.yun.smart.base.TokenParams;
import com.yun.smart.model.OrderComment;
import com.yun.smart.utils.JsonUtils;

/**
 * 批量回复评论请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-24 13:44:41
 */
public class OrderCommentResponseParams extends TokenParams {
	
	private static final long serialVersionUID = 2442872346247236406L;

	/** 商家回复 */
	private String responseContent;
	
	/** 业务IDs */
	private Long[] ids;

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return OrderComment
	 */
	public OrderComment toEntity() {
		OrderComment orderComment = new OrderComment();
		BeanUtils.copyProperties(this, orderComment);
        
		return orderComment;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}