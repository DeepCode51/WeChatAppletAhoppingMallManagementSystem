package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.OrderComment;
import java.util.Date;

/**
 * 删除评论请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-24 13:44:41
 */
public class OrderCommentDeleteParams extends TokenParams {
	
	private static final long serialVersionUID = 3600942552028387840L;
	
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