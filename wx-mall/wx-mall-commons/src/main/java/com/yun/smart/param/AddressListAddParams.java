package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.model.AddressList;

/**
 * 新增参数类 - AddressListAddParams
 * 
 * @author qihh
 * @version 0.0.1
 */
public class AddressListAddParams extends TokenParams{

	private static final long serialVersionUID = 6622725067875098624L;

	/** 用户ID */
	private Long userId;
		
	/** 联系人 */
	private String linkMan;
		
	/** 联系电话 */
	private String linkPhone;
		
	/** 联系地址 */
	private String linkAddr;
		
	/** 默认地址：0=非默认，1=默认 */
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
		
	
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return AddressList
	 */
	public AddressList toEntity() {
		AddressList addressList = new AddressList();
		BeanUtils.copyProperties(this, addressList);
		
		return addressList;
	}
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
}