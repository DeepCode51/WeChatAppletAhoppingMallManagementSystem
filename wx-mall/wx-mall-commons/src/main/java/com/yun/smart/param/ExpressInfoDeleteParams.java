package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.ExpressInfo;

/**
 * 删除订单快递信息请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2019-01-04 18:41:54
 */
public class ExpressInfoDeleteParams extends TokenParams {
	
	private static final long serialVersionUID = -4849889216433693696L;
	
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
	 * @return ExpressInfo
	 */
	public ExpressInfo toEntity() {
		ExpressInfo expressInfo = new ExpressInfo();
		BeanUtils.copyProperties(this, expressInfo);
        
		return expressInfo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}