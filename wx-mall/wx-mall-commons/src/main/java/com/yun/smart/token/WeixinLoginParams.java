package com.yun.smart.token;

import java.io.Serializable;

import com.yun.smart.utils.JsonUtils;

public class WeixinLoginParams implements Serializable {

	private static final long serialVersionUID = 7593244385857535926L;
	
	/** 微信小程序登录后的随机码 */
	private String jscode;

	public String getJscode() {
		return jscode;
	}

	public void setJscode(String jscode) {
		this.jscode = jscode;
	}


	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}
