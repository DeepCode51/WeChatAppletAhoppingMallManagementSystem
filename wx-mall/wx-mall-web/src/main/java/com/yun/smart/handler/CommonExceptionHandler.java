package com.yun.smart.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yun.smart.builder.JsonResultBuilder;
import com.yun.smart.enums.ReturnCode;
import com.yun.smart.exception.AppException;
import com.yun.smart.exception.BussinessException;
import com.yun.smart.exception.MultiResultsException;
import com.yun.smart.exception.NoResultException;
import com.yun.smart.result.JsonResult;

/**
 * 公共异常处理类。
 * 
 */
@ControllerAdvice
public class CommonExceptionHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler(AppException.class)
	@ResponseBody
	public JsonResult appException(AppException e) {
		LOGGER.error("",e);
		ReturnCode returnCode = e.getReturnCode();
		if (returnCode == null) {
			returnCode = ReturnCode.SYSTEM_ERROR;
		}
		return JsonResultBuilder.error(returnCode);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult defaultException(Exception e) {
		LOGGER.error("",e);
		return JsonResultBuilder.error(ReturnCode.SYSTEM_ERROR);
	}
	
	@ExceptionHandler(NoResultException.class)
	@ResponseBody
	public JsonResult noResultException(NoResultException e) {
		LOGGER.error("",e);
		return JsonResultBuilder.error(ReturnCode.NO_RESULT);
	}
	
	@ExceptionHandler(MultiResultsException.class)
	@ResponseBody
	public JsonResult multiResultsException(MultiResultsException e) {
		LOGGER.error("",e);
		return JsonResultBuilder.error(ReturnCode.MULTI_RESULT);
	}
	
	@ExceptionHandler(BussinessException.class)
	@ResponseBody
	public Object bussinessException(BussinessException e) {
		return JsonResultBuilder.error(e.getCode(),e.getMsg());
	}
}
