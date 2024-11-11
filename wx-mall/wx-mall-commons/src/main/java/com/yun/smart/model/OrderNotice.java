package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * dbModel - 消息
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
@TableName("wx_order_notice")
public class OrderNotice extends BaseModel {
	
	private static final long serialVersionUID = 2506956409391906304L;
	
	/** 状态：1=未读 */
	public static final String NOTICE_STATUS_1 = "1";
	/** 状态：2=已读 */
	public static final String NOTICE_STATUS_2 = "2";
	
	/** 标题 */
	@TableField("title")
	private String title;
	/** 订单编号 */
	@TableField("order_no")
	private String orderNo;
	/** 内容 */
	@TableField("content")
	private String content;
	/** 通知类型：1=已付款 2=申请售后 */
	@TableField("notice_type")
	private String noticeType;
	/** 状态：1=未读 2=已读 */
	@TableField("notice_status")
	private String noticeStatus;

	/** @return 标题 */
	public String getTitle() {
		return title;
	}
	/** @param 标题 */
	public void setTitle(String title) {
		this.title = title;
	}
	/** @return 订单编号 */
	public String getOrderNo() {
		return orderNo;
	}
	/** @param 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/** @return 内容 */
	public String getContent() {
		return content;
	}
	/** @param 内容 */
	public void setContent(String content) {
		this.content = content;
	}
	/** @return 通知类型：1=已付款 2=申请售后 */
	public String getNoticeType() {
		return noticeType;
	}
	/** @param 通知类型：1=已付款 2=申请售后 */
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	/** @return 状态：1=未读 2=已读 */
	public String getNoticeStatus() {
		return noticeStatus;
	}
	/** @param 状态：1=未读 2=已读 */
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}