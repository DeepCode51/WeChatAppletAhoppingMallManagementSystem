package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * Entity - 访问日志
 * 
 * @author qihh
 * @version 0.0.1
 */
@TableName("wx_access_log")
public class AccessLog extends BaseModel {
	
	private static final long serialVersionUID = -6865558135950914560L;
	
	/** 用户ID */
	@TableField("user_id")
	private Long userId;
		
	/** 用户名 */
	@TableField("user_name")
	private String userName;
		
	/** 操作类型 */
	@TableField("operate_type")
	private String operateType;
		
	/** 操作名称 */
	@TableField("operate_name")
	private String operateName;
		
	/** 请求方法名 */
	@TableField("operate_method")
	private String operateMethod;
		
	/** 请求URL */
	@TableField("url")
	private String url;
		
	/** 请求参数 */
	@TableField("req_param")
	private String reqParam;
		
	/** 响应结果 */
	@TableField("res_result")
	private String resResult;
		
	/** 执行时长 */
	@TableField("execute_time")
	private Long executeTime;
		
	/** 访问IP */
	@TableField("ip")
	private String ip;
		
			
	/** @return 用户ID */
	public Long getUserId() {
		return userId;
	}

	/** @param userId 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
		
	/** @return 用户名 */
	public String getUserName() {
		return userName;
	}

	/** @param userName 用户名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	/** @return 操作类型 */
	public String getOperateType() {
		return operateType;
	}

	/** @param operateType 操作类型 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
		
	/** @return 操作名称 */
	public String getOperateName() {
		return operateName;
	}

	/** @param operateName 操作名称 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
		
	/** @return 请求方法名 */
	public String getOperateMethod() {
		return operateMethod;
	}

	/** @param operateMethod 请求方法名 */
	public void setOperateMethod(String operateMethod) {
		this.operateMethod = operateMethod;
	}
		
	/** @return 请求URL */
	public String getUrl() {
		return url;
	}

	/** @param url 请求URL */
	public void setUrl(String url) {
		this.url = url;
	}
		
	/** @return 请求参数 */
	public String getReqParam() {
		return reqParam;
	}

	/** @param reqParam 请求参数 */
	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}
		
	/** @return 响应结果 */
	public String getResResult() {
		return resResult;
	}

	/** @param resResult 响应结果 */
	public void setResResult(String resResult) {
		this.resResult = resResult;
	}
		
	/** @return 执行时长 */
	public Long getExecuteTime() {
		return executeTime;
	}

	/** @param executeTime 执行时长 */
	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}
		
	/** @return 访问IP */
	public String getIp() {
		return ip;
	}

	/** @param ip 访问IP */
	public void setIp(String ip) {
		this.ip = ip;
	}
		
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}