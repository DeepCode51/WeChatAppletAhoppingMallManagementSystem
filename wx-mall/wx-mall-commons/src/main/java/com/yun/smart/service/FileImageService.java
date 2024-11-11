package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.FileImage;
import com.yun.smart.param.FileImageAddParams;
import com.yun.smart.param.FileImageDeleteParams;
import com.yun.smart.param.FileImageSearchParams;
import com.yun.smart.param.FileImageUpdateParams;

/**
 * Service - 图片
 * @author qihh
 * @version 0.0.1
 */
public interface FileImageService extends BaseService<FileImage> {

	/**
	 * 分页查询
	 * @param FileImageSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(FileImageSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param FileImageSearchParams
	 * @return List<FileImage>
	 */
	List<FileImage> searchList(FileImageSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param FileImageSearchParams
	 * @return FileImage
	 */
	FileImage searchDetail(FileImageSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param FileImageAddParams
	 */
	void add(FileImageAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param FileImageUpdateParams
	 */
	void update(FileImageUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param FileImageDeleteParams
	 */
	void delete(FileImageDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(FileImageDeleteParams params);

	/**
	 * 上传文件
	 * @param params
	 * @return
	 */
	FileImage uploadImage(FileImageAddParams params);

	/**
	 * 设置主图标识
	 * @param params
	 */
	void changeMainLogo(FileImageUpdateParams params);

}
