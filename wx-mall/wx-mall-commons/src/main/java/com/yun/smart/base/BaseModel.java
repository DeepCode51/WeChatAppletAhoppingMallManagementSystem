package com.yun.smart.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 基础实体-@TableFile（exist=false）实体属性不对应数据库字段的注解
 * @author qihh
 *
 */
public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = 5661784354660535481L;
	
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	/** 删除标识：0为已删除，1为未删除 */
	@TableField("enable")
	private Integer enable;
	
	@TableField("remark")
	private String remark;
	
	@TableField("create_by")
	private Long createBy;
	
	@TableField("create_time")
	private Date createTime;
	
	@TableField("update_by")
	private Long updateBy;
	
	@TableField("update_time")
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
