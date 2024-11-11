package com.yun.smart.base;

import java.util.Date;

/**
 * @author qihh
 *
 */
public class BaseParams extends TokenParams{
	
	private static final long serialVersionUID = 8035497197472851153L;

	private Long id;
	
	/** 删除标识：0为已删除，1为未删除 */
	private Integer enable;
	
	private String remark;
	
	private Long createBy;
	
	private Date createTime;
	
	private Long updateBy;
	
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/** 删除标识：0为已删除，1为未删除 */
	public Integer getEnable() {
		return enable;
	}

	/** 删除标识：0为已删除，1为未删除 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
