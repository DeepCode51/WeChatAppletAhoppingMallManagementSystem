package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.FileImage;

/**
 * 删除图片请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-27 17:00:55
 */
public class FileImageDeleteParams extends TokenParams {
	
	private static final long serialVersionUID = -6509063454732385280L;
	
	/** id */
	private Long id;
	
	/** 业务IDs */
	private Long[] ids;

	/** @return id */
	public Long getId() {
		return id;
	}

	/** @param id */
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return FileImage
	 */
	public FileImage toEntity() {
		FileImage fileImage = new FileImage();
		BeanUtils.copyProperties(this, fileImage);
        
		return fileImage;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}