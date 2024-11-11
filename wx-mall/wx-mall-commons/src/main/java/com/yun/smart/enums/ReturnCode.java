package com.yun.smart.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 返回码
 */
public enum ReturnCode {

    /*********************************************全局ReturnCode(请勿改动)*********************************************/
    SUCCESS("0000", "成功完成操作。"),
    SYSTEM_ERROR("99999", "系统错误！"), 
    ACCESS_FORBIDDEN("90000","无权限访问！"), 
    USER_NOT_LOGIN("90001", "用户未登录！"),
    ERROR_LOGIN("90002", "用户名或密码错误！"),
    FAIL_LOGIN("90003", "登录失败！"),
    
    NO_RESULT("10001", "查询无结果。"),
    MULTI_RESULT("10002", "查询结果不唯一。"),
	BUSSINESS_EXCEPTION("10003", "业务系统异常！"),
	
	EXPORT_EXCEL_ERROR("30001", "导出Excel异常！");

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
    
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<>();
		map.put("msg", this.msg);
		map.put("code", this.code);
		return map;
	}
}
