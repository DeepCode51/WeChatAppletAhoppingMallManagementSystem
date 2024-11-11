package com.yun.smart.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.yun.smart.enums.ReturnCode;
import com.yun.smart.result.JsonResult;

/**
 * 返回结果构建类
 */
public class JsonResultBuilder {

    public static JsonResult ok() {
        return ok(Collections.emptyMap());
    }

    public static JsonResult ok(Object data) {
        return beforeReturn(ReturnCode.SUCCESS, data);
    }
    
    public static JsonResult ok(String key, Object data) {
    	return beforeReturn(ReturnCode.SUCCESS, key, data);
    }

    public static JsonResult error(ReturnCode returnCode) {
        return error(returnCode, Collections.emptyMap());
    }
    
    public static JsonResult error(String code,String msg) {
    	return new JsonResult().setCode(code).setMsg(msg);
    }

    public static JsonResult error(ReturnCode returnCode, Object data) {
        return beforeReturn(returnCode, data);
    }

    /**
     * 返回对象
     * @param returnCode
     * @param data
     * @return
     */
    private static JsonResult beforeReturn(ReturnCode returnCode, Object data) {
    	JsonResult result = new JsonResult().setCode(returnCode.code()).setMsg(returnCode.msg());
    	if (null != data) {
    		if (data instanceof Page) {
    			Map<String,Object> resultMap = new HashMap<>();
    			resultMap.put("pages", ((Page) data).getPages());
    			resultMap.put("size", ((Page) data).getSize());
    			resultMap.put("current", ((Page) data).getCurrent());
    			resultMap.put("total", ((Page) data).getTotal());
    			resultMap.put("list", ((Page) data).getRecords());
    			result.setData(resultMap);
    		} else {
    			result.setData(data);
    		}
    	}
    	return result;
    }

    /**
     * 返回map
     * @param returnCode
     * @param key
     * @param data
     * @return
     */
	private static JsonResult beforeReturn(ReturnCode returnCode, String key, Object data) {
		JsonResult result = new JsonResult().setCode(returnCode.code()).setMsg(returnCode.msg());
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put(key, data);
		result.setData(resultMap);
		return result;
	}
}
