package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.model.FileTips;

/**
 * 新增参数类 - FileTipsAddParams
 * 
 * @author qihh
 * @version 0.0.1
 */
public class FileTipsAddParams extends TokenParams{

	private static final long serialVersionUID = 1990688655890826496L;

	/** 标题 */
	private String title;
		
	/** 内容 */
	private String content;
		
	/** 类型：1=小贴士 2=通知 */
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