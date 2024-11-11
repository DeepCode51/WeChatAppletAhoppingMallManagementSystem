package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.model.GoodsInfo;

/**
 * 删除产品信息请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-21 11:29:27
 */
public class GoodsInfoDeleteParams extends TokenParams {
	
	private static final long serialVersionUID = 4453848260562461696L;
	
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
	 * @return GoodsInfo
	 */
	public GoodsInfo toEntity() {
		GoodsInfo goodsInfo = new GoodsInfo();
		BeanUtils.copyProperties(this, goodsInfo);
        
		return goodsInfo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}