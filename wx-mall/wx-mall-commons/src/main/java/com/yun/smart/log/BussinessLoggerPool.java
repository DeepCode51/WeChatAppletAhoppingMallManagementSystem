package com.yun.smart.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.yun.smart.enums.BussinessType;

/**
 * 
 * 业务日志对象池，单例模式
 */
public class BussinessLoggerPool {

	private static String LOGGER_KEY_SPLIT = "_" ;
	
	private static BussinessLoggerPool bussinessLoggerPool = new BussinessLoggerPool() ;
	
	/**
	 * 日志对象集合。key为"class的包点类名_业务枚举name()"，value是BussinessLogger对象
	 */
	private Map<String, BussinessLogger> loggerMap = new HashMap<>();
	
	private BussinessLoggerPool(){
		
	}

	public static BussinessLoggerPool getInstance(){
		return bussinessLoggerPool ;
	}
	
	public Map<String, BussinessLogger> getLoggerMap() {
		return loggerMap;
	}

	/**
	 * 从池中取出日志对象
	 * @param cls			service对应class
	 * @param bussinessType	业务类型
	 * @return
	 */
	public static BussinessLogger getLogger(Class<?> cls , BussinessType bussinessType){
		String key = cls.getName().concat(LOGGER_KEY_SPLIT).concat(bussinessType.name()) ;
		if(BussinessLoggerPool.getInstance().getLoggerMap().containsKey(key)){
			return BussinessLoggerPool.getInstance().getLoggerMap().get(key);
		}else{
			return new BussinessLogger(bussinessType, LoggerFactory.getLogger(cls)) ;
		}
	}
	
}
