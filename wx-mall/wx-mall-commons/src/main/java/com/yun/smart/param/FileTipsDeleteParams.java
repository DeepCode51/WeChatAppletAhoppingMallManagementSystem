package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.FileTips;

/**
 * 删除请求参数类 - FileTipsDeleteParams
 * 
 * @author qihh
 * @version 0.0.1
 */
public class FileTipsDeleteParams extends TokenParams{
	
	private static final long serialVersionUID = 1990688655890826496L;
	
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
	 * @return FileTips
	 */
	public FileTips toEntity() {
		FileTips fileTips = new FileTips();
		BeanUtils.copyProperties(this, fileTips);
		
		return fileTips;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}