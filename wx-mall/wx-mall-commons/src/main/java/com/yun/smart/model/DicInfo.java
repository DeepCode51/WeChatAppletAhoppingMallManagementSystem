package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * Entity - 字典
 * 
 * @author qihh
 * @version 0.0.1
 */
@TableName("wx_dic_info")
public class DicInfo extends BaseModel {
	
	private static final long serialVersionUID = 6634980878038003712L;
	
	/** 父节点ID  默认0 */
	@TableField("parent_id")
	private Long parentId;
		
	/** 字典名词 */
	@TableField("dic_name")
	private String dicName;
		
	/** 字典值 */
	@TableField("dic_value")
	private String dicValue;
		
	/** 字典词 */
	@TableField("dic_text")
	private String dicText;
		
			
	/** @return 父节点ID  默认0 */
	public Long getParentId() {
		return parentId;
	}

	/** @param parentId 父节点ID  默认0 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
		
	/** @return 字典名词 */
	public String getDicName() {
		return dicName;
	}

	/** @param dicName 字典名词 */
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
		
	/** @return 字典值 */
	public String getDicValue() {
		return dicValue;
	}

	/** @param dicValue 字典值 */
	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
		
	/** @return 字典词 */
	public String getDicText() {
		return dicText;
	}

	/** @param dicText 字典词 */
	public void setDicText(String dicText) {
		this.dicText = dicText;
	}
		
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}