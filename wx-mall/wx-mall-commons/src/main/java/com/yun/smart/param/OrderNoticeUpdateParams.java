package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.OrderNotice;

/**
 * 更新消息请求参数类 
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:28
 */
public class OrderNoticeUpdateParams extends TokenParams {
	
	private static final long serialVersionUID = -7683782955262405632L;
	
	/** id */
	private Long id;
	
	/** 标题 */
	private String title;
	/** 订单编号 */
	private String orderNo;
	/** 内容 */
	private String content;
	/** 通知类型：1=已付款 2=申请售后 */
	private String noticeType;
	/** 状态：1=未读 2=已读 */
	private String noticeStatus;

	/** @return id */
	public Long getId() {
		return id;
	}

	/** @param id */
	public void setId(Long id) {
		this.id = id;
	}

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