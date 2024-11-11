package com.yun.smart.param;

import org.springframework.beans.BeanUtils;
import com.yun.smart.utils.JsonUtils;
import com.yun.smart.base.TokenParams;
import com.yun.smart.model.AccessLog;

/**
 * 新增参数类 - AccessLogAddParams
 * 
 * @author qihh
 * @version 0.0.1
 */
public class AccessLogAddParams extends TokenParams{

	private static final long serialVersionUID = -6865558135950914560L;

	/** 用户ID */
	private Long userId;
		
	/** 用户名 */
	private String userName;
		
	/** 操作类型 */
	private String operateType;
		
	/** 操作名称 */
	private String operateName;
		
	/** 请求方法名 */
	private String operateMethod;
		
	/** 请求URL */
	private String url;
		
	/** 请求参数 */
	private String reqParam;
		
	/** 响应结果 */
	private String resResult;
		
	/** 执行时长 */
	private Long executeTime;
		
	/** 访问IP */
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
		
	
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return AccessLog
	 */
	public AccessLog toEntity() {
		AccessLog accessLog = new AccessLog();
		BeanUtils.copyProperties(this, accessLog);
		
		return accessLog;
	}
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
}