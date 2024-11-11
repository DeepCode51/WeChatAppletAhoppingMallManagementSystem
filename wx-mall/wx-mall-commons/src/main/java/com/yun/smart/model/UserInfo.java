package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * Entity - 用户
 * 
 * @author qihh
 * @version 0.0.1
 */
@TableName("wx_user_info")
public class UserInfo extends BaseModel {
	
	private static final long serialVersionUID = -6168468568811668480L;
	
	/** 姓名 */
	@TableField("user_name")
	private String userName;
		
	/** 手机号 */
	@TableField("phone")
	private String phone;
		
	/** 密码 */
	@TableField("passwd")
	private String passwd;
		
	/** 微信号 */
	@TableField("wx_account")
	private String wxAccount;
		
	/** 微信小程序openid */
	@TableField("wx_openid")
	private String wxOpenid;
		
	/** 类型：1=超级管理员 2=授权管理员 3=用户 */
	@TableField("user_type")
	private String userType;
		
			
	/** @return 姓名 */
	public String getUserName() {
		return userName;
	}

	/** @param userName 姓名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	/** @return 手机号 */
	public String getPhone() {
		return phone;
	}

	/** @param phone 手机号 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
	/** @return 密码 */
	public String getPasswd() {
		return passwd;
	}

	/** @param passwd 密码 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
		
	/** @return 微信号 */
	public String getWxAccount() {
		return wxAccount;
	}

	/** @param wxAccount 微信号 */
	public void setWxAccount(String wxAccount) {
		this.wxAccount = wxAccount;
	}
		
	/** @return 微信小程序openid */
	public String getWxOpenid() {
		return wxOpenid;
	}

	/** @param wxOpenid 微信小程序openid */
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
		
	/** @return 类型：1=超级管理员 2=授权管理员 3=用户 */
	public String getUserType() {
		return userType;
	}

	/** @param userType 类型：1=超级管理员 2=授权管理员 3=用户 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
		
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}