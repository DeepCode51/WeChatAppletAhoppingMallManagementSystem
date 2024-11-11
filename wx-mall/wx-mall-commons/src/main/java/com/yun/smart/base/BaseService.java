package com.yun.smart.base;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * 基础接口
 * @author qihh
 *
 * @param <T>
 */
public interface BaseService<T extends BaseModel> extends IService<T> {

	/**
	 * 查询列表
	 * @param model
	 * @return List<T>
	 */
	List<T> getList(T model);

	/**
	 * 查询详情
	 * @param model
	 * @return T
	 */
	T getDetail(T model);

	/**
	 * 添加数据
	 * @param model
	 */
	void addModel(T model, Long userId);
	
	/**
	 * 修改数据
	 * @param model
	 */
	void updateModel(T model, Long userId);
	
	/**
	 * 删除明细
	 * @param model
	 */
	void deleteModel(T model, Long userId);
}
