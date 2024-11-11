package com.yun.smart.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.yun.smart.exception.BussinessException;

public class AssertUtil {
	
	/**
	 * 不为true则抛出业务异常
	 * @param expression
	 * @param key
	 */
	public static void isTrue(boolean expression, String key) {
		if (!expression) {
			throw new BussinessException(key);
		}
	}

	/**为空则抛出业务异常*/
	public static void isNull(Object object, String key) {
		if (object != null) {
			throw new BussinessException(key);
		}
	}

	/**字符串为空或长度为0抛出异常*/
	public static void hasLength(String text, String key) {
		if (StringUtils.isEmpty(text)) {
			throw new BussinessException(key);
		}
	}

	/**  */
	public static void hasText(String text, String key) {
		if (StringUtils.isBlank(text)) {
			throw new BussinessException(key);
		}
	}
	
	
	/** 允许最小值，value小于min则抛出异常 */
	public static void min(Integer value, Integer min, String key) {
		notNull(value, key);
		if (value < min) {
			throw new BussinessException(key);
		}
	}

	/** 允许最大值，value大于max则抛出异常 */
	public static void max(Integer value, Integer max, String key) {
		notNull(value, key);
		if (value > max) {
			throw new BussinessException(key);
		}
	}

	/** 允许值范围， min<=value<=max */
	public static void range(Integer value, Integer min, Integer max, String key) {
		min(value, min, key);
		max(value, max, key);
	}

	/** 允许最小值，value小于min则抛出异常 */
	public static void min(Float value, Float min, String key) {
		notNull(value, key);
		if (value < min) {
			throw new BussinessException(key);
		}
	}

	/** 允许最大值，value大于max则抛出异常 */
	public static void max(Float value, Float max, String key) {
		notNull(value, key);
		if (value > max) {
			throw new BussinessException(key);
		}
	}
	
	/** 允许值范围， min<=value<=max */
	public static void range(Float value, Float min, Float max, String key) {
		min(value, min, key);
		max(value, max, key);
	}

	/** 允许最小值，value小于min则抛出异常 */
	public static void min(Double value, Double min, String key) {
		notNull(value, key);
		if (value < min) {
			throw new BussinessException(key);
		}
	}
	
	/** 允许最大值，value大于max则抛出异常 */
	public static void max(Double value, Double max, String key) {
		notNull(value, key);
		if (value > max) {
			throw new BussinessException(key);
		}
	}
	/** 断言a>b,如果a<=b则抛出异常 */
	public static void isGreate(double a, double b, String msg) {
		if (a <= b) {
			throw new BussinessException(msg);
		}
	}
	
	/** 断言a<b,如果a>=b则抛出异常 */
	public static void isLess(double a, double b, String msg) {
		isGreate(b,a,msg);
	}
	
	/** 断言a==b,如果a != b则抛出异常 */
	public static void isEqual(double a, double b, String msg) {
		if (a != b) {
			throw new BussinessException(msg);
		}
	}
	
	/** 断言a>=b,如果a<b则抛出异常 */
	public static void isGreateEqual(double a, double b, String msg) {
		if (a < b) {
			throw new BussinessException(msg);
		}
	}
	
	/** 断言a<=b,如果a>b则抛出异常 */
	public static void isLessEqual(double a, double b, String msg) {
		if (a > b) {
			throw new BussinessException(msg);
		}
	}

	/** 允许值范围， min<=value<=max */
	public static void range(Double value, Double min, Double max, String key) {
		min(value, min, key);
		max(value, max, key);
	}
	
	public static void notNull(Object object, String key) {
		if (isEmpty(object)) {
			throw new BussinessException(key);
		}
	}
	
	public static final boolean isEmpty(Object pObj) {
		if (pObj == null || "".equals(pObj))
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).trim().length() == 0 || "undefined".equals(pObj)) {
				return true;
			}
		} else if (pObj instanceof Collection<?>) {
			if (((Collection<?>) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map<?, ?>) {
			if (((Map<?, ?>) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**集合是否为空，为空抛出异常*/
	public static void isNotEmpty(List list, String msg) {
		notNull(list, msg);
		if (list.isEmpty()) {
			throw new BussinessException(msg);
		}
	}
	
	/**集合不为空抛出异常*/
	public static void isEmpty(List list, String msg) {
		if (null != list && !list.isEmpty()) {
			throw new BussinessException(msg);
		}
	}
	
	/**比较字符串 src,target 不相等相等则抛出异常*/
	public static void equals(String src,String target, String msg) {
		if (!src.equals(target)) {
			throw new BussinessException(msg);
		}
	}
	/**比较字符串 src,target 相等相等则抛出异常*/
	public static void notEquals(String src,String target, String msg) {
		if (src.equals(target)) {
			throw new BussinessException(msg);
		}
	}

	/** 邮箱 */
	public static void email(String text,String msg) {
		String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		pattern(text, regex, true, msg);
	}

	/** 手机号 */
	public static void mobile(String text,String msg) {
		String regex = "((^(13|15|17|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		pattern(text, regex, true, msg);
	}
	
	/** 允许带一位小数为0的百分比 */
	public static void proportion(String text,String msg) {
		String regex = "^(0|[1-9][0-9]?|100)(\\.[0]{1})?$";
		pattern(text, regex, true, msg);
	}
	
	/** 允许带1到2位小数百分比 */
	public static void proportion(String text,int num,String msg) {
		String regex = "^(0|[1-9][0-9]?)(\\.[0-9]{1,2})?|(100(\\.[0]{1,2}))$";
		pattern(text, regex, true, msg);
	}
	
	/** 正整数 */
	public static void number(String text,String msg) {
		String regex = "^[1-9]+[0-9]*$";
		pattern(text, regex, true, msg);
	}
	
	/** 0或者正整数 */
	public static void term(String text,String msg) {
		String regex = "^0|[1-9]+[0-9]*$";
		pattern(text, regex, true, msg);
	}
	
	/** 发薪日 */
	public static void payDay(String text,String msg) {
		String regex = "^([1-9]|[1-2][0-9]?|(3[0-1]))$";
		pattern(text, regex, true, msg);
	}

	/** 正则表达式 */
	public static void pattern(String text, String regex, boolean flag, String key) {
		boolean result = false;
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			result = matcher.matches();
		} catch (Exception e) {
			result = false;
		}
		if (result != flag) {
			throw new BussinessException(key);
		}
	}

	public static void main(String[] args) {
		AssertUtil.proportion("100.00",2, "格式错误");
	}

}
