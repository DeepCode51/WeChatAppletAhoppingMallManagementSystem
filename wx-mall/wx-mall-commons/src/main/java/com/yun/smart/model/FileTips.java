package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * Entity - 文章
 * 
 * @author qihh
 * @version 0.0.1
 */
@TableName("wx_file_tips")
public class FileTips extends BaseModel {
	
	private static final long serialVersionUID = 1990688655890826496L;
	
	/** 标题 */
	@TableField("title")
	private String title;
		
	/** 内容 */
	@TableField("content")
	private String content;
		
	/** 类型：1=小贴士 2=通知 */
	@TableField("biz_type")
	private String bizType;
		
			
	/** @return 标题 */
	public String getTitle() {
		return title;
	}

	/** @param title 标题 */
	public void setTitle(String title) {
		this.title = title;
	}
		
	/** @return 内容 */
	public String getContent() {
		return content;
	}

	/** @param content 内容 */
	public void setContent(String content) {
		this.content = content;
	}
		
	/** @return 类型：1=小贴士 2=通知 */
	public String getBizType() {
		return bizType;
	}

	/** @param bizType 类型：1=小贴士 2=通知 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
		
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}