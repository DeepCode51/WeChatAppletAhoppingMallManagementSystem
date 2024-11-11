package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;
import java.util.Date;

/**
 * dbModel - 评论
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-24 13:44:41
 */
@TableName("wx_order_comment")
public class OrderComment extends BaseModel {
	
	private static final long serialVersionUID = 4337620008448820224L;
	
	/** 类型：1=评论 */
	public static final String COMMENT_TYPE_1 = "1";
	/** 类型：2=售后 */
	public static final String COMMENT_TYPE_2 = "2";
	
	/** 用户ID */
	@TableField("user_id")
	private Long userId;
	/** 订单编号 */
	@TableField("order_no")
	private String orderNo;
	/** 产品编号 */
	@TableField("goods_no")
	private String goodsNo;
	/** 评论内容：500字以内 */
	@TableField("content")
	private String content;
	/** 评分等级：1到5分 */
	@TableField("goods_star")
	private Integer goodsStar;
	/** 商家回复 */
	@TableField("response_content")
	private String responseContent;
	/** 商家回复时间 */
	@TableField("response_time")
	private Date responseTime;
	/** 类型：1=评论 2=售后 */
	@TableField("comment_type")
	private String commentType;

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

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}