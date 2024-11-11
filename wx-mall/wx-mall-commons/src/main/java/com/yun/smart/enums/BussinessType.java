package com.yun.smart.enums;

/**
 * 
 * 日志-业务类型
 */
public enum BussinessType {

	ACCESSLOG("访问日志"),
	ADDRESSLIST("地址"),
	FILEIMAGE("图片"),
	FILETIPS("文章"),
	GOODSINFO("产品信息"),
	ORDERCOMMENT("评论"),
	ORDERINFO("订单"),
	ORDERNOTICE("消息"),
	USERINFO("用户"),
	DICINFO("字典"),
	EXPRESSINFO("快递信息"),
	AUTH("登录鉴权"),
	UTIL("工具类");

	/**
	 * 业务名称
	 */
	private String name;
	
	private BussinessType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
