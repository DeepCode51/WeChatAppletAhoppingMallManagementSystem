package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * Entity - 地址
 * 
 * @author qihh
 * @version 0.0.1
 */
@TableName("wx_address_list")
public class AddressList extends BaseModel {
	
	private static final long serialVersionUID = 6622725067875098624L;
	
	/** 默认地址：0=非默认 */
	public static final Integer NOT_DEFAULT_ADDR = 0;
	
	/** 默认地址：1=默认 */
	public static final Integer IS_DEFAULT_ADDR = 1;
	
	/** 用户ID */
	@TableField("user_id")
	private Long userId;
		
	/** 联系人 */
	@TableField("link_man")
	private String linkMan;
		
	/** 联系电话 */
	@TableField("link_phone")
	private String linkPhone;
		
	/** 联系地址 */
	@TableField("link_addr")
	private String linkAddr;
		
	/** 默认地址：0=非默认，1=默认 */
	@TableField("default_addr")
	private Integer defaultAddr;
		
			
	/** @return 用户ID */
	public Long getUserId() {
		return userId;
	}

	/** @param userId 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
		
	/** @return 联系人 */
	public String getLinkMan() {
		return linkMan;
	}

	/** @param linkMan 联系人 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
		
	/** @return 联系电话 */
	public String getLinkPhone() {
		return linkPhone;
	}

	/** @param linkPhone 联系电话 */
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
		
	/** @return 联系地址 */
	public String getLinkAddr() {
		return linkAddr;
	}

	/** @param linkAddr 联系地址 */
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}
		
	/** @return 默认地址：0=非默认，1=默认 */
	public Integer getDefaultAddr() {
		return defaultAddr;
	}

	/** @param defaultAddr 默认地址：0=非默认，1=默认 */
	public void setDefaultAddr(Integer defaultAddr) {
		this.defaultAddr = defaultAddr;
	}
		
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}