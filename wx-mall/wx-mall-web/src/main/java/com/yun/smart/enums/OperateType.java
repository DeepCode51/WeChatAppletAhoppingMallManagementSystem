package com.yun.smart.enums;

public enum OperateType {

	UNKNOWN("0","未知操作"),
	ADD("1","新增"),
	DELETE("2","删除"),
	MODIFY("3","修改"),
	SEARCH("4","查询");
	
	OperateType(String code, String msg) {
		this.code = code;
        this.msg = msg;
	}
	
	/**
     * 操作编码
     */
    private String code;

    /**
     * 操作说明
     */
    private String msg;

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
    
    
}
