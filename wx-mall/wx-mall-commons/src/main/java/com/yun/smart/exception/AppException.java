package com.yun.smart.exception;

import com.yun.smart.enums.ReturnCode;

/**
 * 业务异常类
 *
 * @author qihh
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 3888804516406453808L;

    private ReturnCode returnCode;

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public AppException() {
    }

    public AppException(ReturnCode returnCode) {
        super("错误码[".concat(returnCode.code()).concat("]:").concat(returnCode.msg()));
        this.returnCode = returnCode;
    }

    public AppException(ReturnCode returnCode, Exception e) {
        super(e);
        this.returnCode = returnCode;
    }

}
