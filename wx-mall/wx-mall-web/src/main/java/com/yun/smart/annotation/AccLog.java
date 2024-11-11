package com.yun.smart.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yun.smart.enums.OperateType;

/**
 * @description 操作切面注解
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccLog {

	/**
	 * 操作名称
	 */
	String operateName() default "";
	/**
	 * 操作类型
	 */
	OperateType operateType() default OperateType.UNKNOWN;
}
