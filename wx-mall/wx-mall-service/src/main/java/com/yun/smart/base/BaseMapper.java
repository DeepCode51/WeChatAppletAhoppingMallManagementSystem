package com.yun.smart.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseMapper<T extends BaseModel> extends com.baomidou.mybatisplus.mapper.BaseMapper<T>	 {

	/**
	 * 查询结果集-支持分页
	 * @param page
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> searchPage(RowBounds rowBounds, BaseParams params);
	
	/**
	 * 查询结果集-不分页
	 * @param testDemo
	 * @return
	 */
	List<T> searchList(T t);

}
