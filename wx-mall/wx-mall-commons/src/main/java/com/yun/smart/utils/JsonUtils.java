package com.yun.smart.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.yun.smart.config.GsonIgnoreStrategy;

/**
 * Created by blue on 2017/5/20.
 * <p>
 * Json工具类（使用Gson作为底层实现）
 */
public class JsonUtils {

    /**
     * 构建统一实现对象
     */
    private static final Gson gson = new GsonBuilder()
            .addSerializationExclusionStrategy(new GsonIgnoreStrategy())
//            .serializeNulls()
            .create();

    /**
     * 将对象转化为Json字符串
     *
     * @param src 转化对象
     * @return Json字符串
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    /**
     * 将复杂对象转化为Json字符串
     *
     * @param src       转化对象
     * @param typeOfSrc 复杂对象具体类型（主要用于复杂泛型对象）
     * @return Json字符串
     */
    public static String toJson(Object src, Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * 将Json字符串转化为指定对象
     *
     * @param json     Json字符串
     * @param classOfT 对象Class
     * @return Class对应对象
     */
    public static <T> T jsonToObject(String json, Class<T> classOfT) {
        if (StringUtils.isNotBlank(json)) {
        	return gson.fromJson(json, classOfT);
        }
        return null;
    }
    
    /**
	 * 将Json字符串转化为具体对象
	 *
	 * @param json    Json字符串
	 * @param typeOfT 复杂对象具体类型（主要用于复杂泛型对象）
	 * @return Class对应对象
	 */
	public static <T> T jsonToObject(String json, Type typeOfT) {
		if (StringUtils.isNotBlank(json)) {
			return gson.fromJson(json, typeOfT);
		}
		return null;
	}

    
    /**
     * 将Json字符串转化为指定对象集合
     * @param json
     * @param cls
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
    	if (StringUtils.isNotBlank(json)) {
    		List<T> list = new ArrayList<T>();
    		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
    		for(final JsonElement elem : array){
    			list.add(gson.fromJson(elem, cls));
    		}
    		return list;
    	}
    	return null;
    }


    /**
     * 转成list中有map的
     * 
     * @param json
     * @return
     */
    public static <T> List<Map<String, T>> jsonToListMap(String json) {
    	if (StringUtils.isNotBlank(json)) {
    		return gson.fromJson(json,new TypeToken<List<Map<String, T>>>() {}.getType());
    	}
    	return null;
    }
 
 
    /**
     * 转成map的
     * 
     * @param json
     * @return
     */
    public static <T> Map<String, T> jsonToMap(String json) {
    	if (StringUtils.isNotBlank(json)) {
    		return gson.fromJson(json, new TypeToken<Map<String, T>>() {}.getType());
    	}
    	return null;
    }
    
    
    /**
     * 将对象转化为Gson的JsonObject对象
     *
     * @param src 转化的对象
     * @return JsonObject对象
     */
    public static JsonObject toJsonObject(Object src) {
    	if (src != null) {
    		return gson.toJsonTree(src).getAsJsonObject();
    	}
    	return null;
    }
}
