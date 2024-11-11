package com.yun.smart.token;

import java.io.Serializable;

import com.yun.smart.utils.JsonUtils;

/**
 * 小程序登录凭证
 * @author qihh
 * @date 2018年12月27日
 */
public class WeixinToken implements Serializable {

	private static final long serialVersionUID = -3616806982529243174L;
	
	/** 用户数据加密密钥 */
	private String session_key;
	/** 用户唯一凭证 */
	private String openid;
	
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
	

}
