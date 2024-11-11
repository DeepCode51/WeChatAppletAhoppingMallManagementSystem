package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.OrderComment;
import java.util.Date;

/**
 * 更新评论请求参数类 
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-24 13:44:41
 */
public class OrderCommentUpdateParams extends TokenParams {
	
	private static final long serialVersionUID = 991340654015849856L;
	
	/** id */
	private Long id;
	
	/** 用户ID */
	private Long userId;
	/** 订单编号 */
	private String orderNo;
	/** 产品编号 */
	private String goodsNo;
	/** 评论内容：500字以内 */
	private String content;
	/** 评分等级：1到5分 */
	private Integer goodsStar;
	/** 商家回复 */
	private String responseContent;
	/** 商家回复时间 */
	private Date responseTime;
	/** 类型：1=评论 2=售后 */
	private String commentType;

	/** @return id */
	public Long getId() {
		return id;
	}

	/** @param id */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return 用户ID */
	public Long getUserId() {
		return userId;
	}
	/** @param 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/** @return 订单编号 */
	public String getOrderNo() {
		return orderNo;
	}
	/** @param 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/** @return 产品编号 */
	public String getGoodsNo() {
		return goodsNo;
	}
	/** @param 产品编号 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	/** @return 评论内容：500字以内 */
	public String getContent() {
		return content;
	}
	/** @param 评论内容：500字以内 */
	public void setContent(String content) {
		this.content = content;
	}
	/** @return 评分等级：1到5分 */
	public Integer getGoodsStar() {
		return goodsStar;
	}
	/** @param 评分等级：1到5分 */
	public void setGoodsStar(Integer goodsStar) {
		this.goodsStar = goodsStar;
	}
	/** @return 商家回复 */
	public String getResponseContent() {
		return responseContent;
	}
	/** @param 商家回复 */
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	/** @return 商家回复时间 */
	public Date getResponseTime() {
		return responseTime;
	}
	/** @param 商家回复时间 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	/** @return 类型：1=评论 2=售后 */
	public String getCommentType() {
		return commentType;
	}
	/** @param 类型：1=评论 2=售后 */
	public void setCommentType(String commentType) {
		this.commentType = commentType;
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