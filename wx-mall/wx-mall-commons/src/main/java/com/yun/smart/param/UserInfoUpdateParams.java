package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.UserInfo;

/**
 * 更新请求参数类 - UserInfoUpdateParams
 * 
 * @author qihh
 * @version 0.0.1
 */
public class UserInfoUpdateParams extends TokenParams{
	
	private static final long serialVersionUID = -6168468568811668480L;
	
	/** 业务ID */
	private Long id;
	
	/** 姓名 */
	private String userName;
		
	/** 手机号 */
	private String phone;
		
	/** 密码 */
	private String passwd;
		
	/** 微信号 */
	private String wxAccount;
		
	/** 微信小程序openid */
	private String wxOpenid;
		
	/** 类型：1=超级管理员 2=授权管理员 3=用户 */
	private String userType;
		
	
	/** @return 业务ID */
	public Long getId() {
		return id;
	}

	/** @param id 业务ID */
	public void setId(Long id) {
		this.id = id;
	}
	
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
		
			
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return UserInfo
	 */
	public UserInfo toEntity() {
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(this, userInfo);
		
		return userInfo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}