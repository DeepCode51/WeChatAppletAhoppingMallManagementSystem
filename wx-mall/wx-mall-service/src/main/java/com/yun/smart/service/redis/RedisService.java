package com.yun.smart.service.redis;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yun.smart.utils.JsonUtils;

/**
 * redis服务-工具类
 * @author bonadeIT
 *
 */
@Service("redisService")
public class RedisService {

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 指定缓存失效时间
	 * @param key
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public boolean expire(String key, long timeout, TimeUnit timeUnit) {
		return redisTemplate.expire(key, timeout, timeUnit);
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	/**
	 * 设置缓存 - 永久有效
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 设置缓存 - 自定义超时时间
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	public void put(String key, String value, int timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public String get(String key) {
		if (StringUtils.isNotBlank(key)) {
			return redisTemplate.opsForValue().get(key);
		}
		return null;
	}
	
	/**
	 * 放入对象 - 永久有效
	 * @param key
	 * @param obj
	 */
	public <T> void put(String key, T obj) {
		if (StringUtils.isNotBlank(key) && null != obj) {
			put(key, JsonUtils.toJson(obj));
		}
	}

	/**
	 * 放入对象 - 自定义超时时间
	 * @param key
	 * @param obj
	 * @param timeout
	 * @param unit
	 */
	public <T> void put(String key, T obj, int timeout, TimeUnit unit) {
		put(key, JsonUtils.toJson(obj), timeout, unit);
	}
	
	/**
	 * 获取对象
	 * @param key
	 * @param cls
	 * @return
	 */
	public <T> T get(String key, Class<T> cls) {
		String json = get(key);
		return JsonUtils.jsonToObject(json, cls);
	}
	
	/**
	 * 获取集合对象
	 * @param key
	 * @param cls
	 * @param collectionClass
	 * @return
	 */
	public <T> T get(String key, Type typeOfSrc) {
		String json = get(key);
		return JsonUtils.jsonToObject(json, typeOfSrc);
	}
}
