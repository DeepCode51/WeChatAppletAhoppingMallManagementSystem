package com.yun.smart.result;

import java.io.Serializable;

import com.yun.smart.enums.ReturnCode;
import com.yun.smart.utils.JsonUtils;

/**
 * 统一结果返回
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = -8519279517546253367L;

	/**
     * 返回错误码
     */
    private String code;

    /**
     * 返回错误信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    public JsonResult() {
        super();
    }

    public JsonResult(ReturnCode returnCode) {
        super();
        this.code = returnCode.code();
        this.msg = returnCode.msg();
    }

    public String getCode() {
        return code;
    }

    public JsonResult setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
