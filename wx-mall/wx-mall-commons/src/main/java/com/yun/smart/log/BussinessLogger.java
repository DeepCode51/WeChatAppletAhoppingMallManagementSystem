package com.yun.smart.log;

import org.slf4j.Logger;
import com.yun.smart.enums.BussinessType;

/**
 * 
 * 日志输出模型
 */	
public class BussinessLogger {

	private static final String LOG_FORMAT = "[{bussinessType}]:{content}" ;
	
	/**
	 * 业务类型
	 */
	private BussinessType bussinessType ;
	
	/**
	 * 日志对象
	 */
	private Logger logger ;
	
	public BussinessLogger(){
		
	}
	
	public BussinessLogger(BussinessType bussinessType,Logger logger){
		this.bussinessType = bussinessType ;
		this.logger = logger ;
	}

	public BussinessType getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(BussinessType bussinessType) {
		this.bussinessType = bussinessType;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	private String output(String content){
		return LOG_FORMAT.replace("{bussinessType}", this.bussinessType.getName())
				.replace("{content}", content);
	}
	
	public void info(String content){
		this.logger.info(this.output(content));
	}

	public void info(String content, Object... params){
		this.logger.info(this.output(content), params);
	}
	
	public void trace(String content){
		this.logger.trace(this.output(content));
	}
	
	public void debug(String content){
		this.logger.debug(this.output(content));
	}
	
	public void debug(String content, Object... params){
		this.logger.debug(this.output(content), params);
	}
	
	public void warn(String content){
		this.logger.warn(this.output(content));
	}
	
	public void error(String content){
		this.logger.error(this.output(content));
	}
	
}
