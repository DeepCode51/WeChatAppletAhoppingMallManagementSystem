package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.FileTips;
import com.yun.smart.param.FileTipsAddParams;
import com.yun.smart.param.FileTipsDeleteParams;
import com.yun.smart.param.FileTipsSearchParams;
import com.yun.smart.param.FileTipsUpdateParams;

/**
 * Service - 文章
 * @author qihh
 * @version 0.0.1
 */
public interface FileTipsService extends BaseService<FileTips> {

	/**
	 * 分页查询
	 * @param FileTipsSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(FileTipsSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param FileTipsSearchParams
	 * @return List<FileTips>
	 */
	List<FileTips> searchList(FileTipsSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param FileTipsSearchParams
	 * @return FileTips
	 */
	FileTips searchDetail(FileTipsSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param FileTipsAddParams
	 */
	void add(FileTipsAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param FileTipsUpdateParams
	 */
	void update(FileTipsUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param FileTipsDeleteParams
	 */
	void delete(FileTipsDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(FileTipsDeleteParams params);

}
