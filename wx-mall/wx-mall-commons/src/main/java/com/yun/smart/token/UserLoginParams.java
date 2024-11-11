package com.yun.smart.token;

import java.io.Serializable;

import com.yun.smart.utils.JsonUtils;

public class UserLoginParams implements Serializable {

	private static final long serialVersionUID = -9125842903959905149L;
	
	/**
	 * 登录账号
	 */
	private String account;
	
	/**
	 * 登录密码
	 */
	private String passwd;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}
