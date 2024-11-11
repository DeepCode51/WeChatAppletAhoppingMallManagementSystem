package com.yun.smart.base;

import java.io.Serializable;

/**
 * 基础参数
 * @author qihh
 *
 */
public class TokenParams implements Serializable{
	
	private static final long serialVersionUID = -366064148323336029L;

	/** 登录token */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
