package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.AccessLog;

/**
 * 删除请求参数类 - AccessLogDeleteParams
 * 
 * @author qihh
 * @version 0.0.1
 */
public class AccessLogDeleteParams extends TokenParams{
	
	private static final long serialVersionUID = -6865558135950914560L;
	
	/** 业务ID */
	private Long id;
	
	/** 业务IDs */
	private Long[] ids;
	
	/** @return 业务ID */
	public Long getId() {
		return id;
	}
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	/** @param id 业务ID */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return AccessLog
	 */
	public AccessLog toEntity() {
		AccessLog accessLog = new AccessLog();
		BeanUtils.copyProperties(this, accessLog);
		
		return accessLog;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}