package com.yun.smart.exception;

import com.google.gson.JsonObject;
import com.yun.smart.enums.ReturnCode;

/**
 * 业务逻辑异常
 */
public class BussinessException extends RuntimeException {
	
	private static final long serialVersionUID = -8181410179004664001L;

	/**
     * 返回码
     */
    private String code;
    
    public BussinessException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = ReturnCode.BUSSINESS_EXCEPTION.code();
		this.msg = message;
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
		this.code = ReturnCode.BUSSINESS_EXCEPTION.code();
		this.msg = message;
	}

	public BussinessException(String message) {
		super(message);
		this.code = ReturnCode.BUSSINESS_EXCEPTION.code();
		this.msg = message;
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

	/**
     * 返回信息
     */
    private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		JsonObject obj = new JsonObject();
		obj.addProperty("code", code);
		obj.addProperty("msg", msg);
		return obj.toString();
	}

}
