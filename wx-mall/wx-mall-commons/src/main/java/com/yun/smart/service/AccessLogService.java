package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.AccessLog;
import com.yun.smart.param.AccessLogAddParams;
import com.yun.smart.param.AccessLogDeleteParams;
import com.yun.smart.param.AccessLogSearchParams;
import com.yun.smart.param.AccessLogUpdateParams;

/**
 * Service - 访问日志
 * @author qihh
 * @version 0.0.1
 */
public interface AccessLogService extends BaseService<AccessLog> {

	/**
	 * 分页查询
	 * @param AccessLogSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(AccessLogSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param AccessLogSearchParams
	 * @return List<AccessLog>
	 */
	List<AccessLog> searchList(AccessLogSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param AccessLogSearchParams
	 * @return AccessLog
	 */
	AccessLog searchDetail(AccessLogSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param AccessLogAddParams
	 */
	void add(AccessLogAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param AccessLogUpdateParams
	 */
	void update(AccessLogUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param AccessLogDeleteParams
	 */
	void delete(AccessLogDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(AccessLogDeleteParams params);

}
