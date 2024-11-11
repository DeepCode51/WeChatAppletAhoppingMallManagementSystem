package com.yun.smart.mapper;

import java.util.List;

import com.yun.smart.base.BaseMapper;
import com.yun.smart.model.DicInfo;

/**
 * Mapper - 字典
 * @author qihh
 * @version 0.0.1
 *
 */
public interface DicInfoMapper extends BaseMapper<DicInfo> {

	/**
	 * 查询字典组
	 * @param entity
	 * @return
	 */
	List<DicInfo> searchParents(DicInfo entity);
	

}