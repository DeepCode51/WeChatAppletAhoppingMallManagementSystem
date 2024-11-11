package com.yun.smart.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.FileImageMapper;
import com.yun.smart.model.FileImage;
import com.yun.smart.param.FileImageAddParams;
import com.yun.smart.param.FileImageDeleteParams;
import com.yun.smart.param.FileImageSearchParams;
import com.yun.smart.param.FileImageUpdateParams;
import com.yun.smart.service.FileImageService;
import com.yun.smart.utils.AssertUtil;

/**
 * ServiceImpl - 图片
 * @author qihh
 * @version 0.0.1
 */
@Service("fileImageService")
public class FileImageServiceImpl extends BaseServiceImpl<FileImageMapper,FileImage> implements FileImageService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.FILEIMAGE);

	@Resource
	private FileImageMapper fileImageMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(FileImageSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		page.setOrderByField("biz_no");
		
		logger.info("FileImageService-分页查询图片入参:{}",params);
		List<Map<String,Object>> result = fileImageMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<FileImage> searchList(FileImageSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public FileImage searchDetail(FileImageSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(FileImageAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.addModel(params.toEntity(),userId);
	}

	@Override
	public void update(FileImageUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(FileImageDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(FileImageDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		FileImage fileImage = null;
		List<FileImage> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			fileImage = new FileImage();
			fileImage.setId(id);
			fileImage.setEnable(BooleanValue.FALSE.value());
			fileImage.setUpdateTime(updateDate);
			fileImage.setUpdateBy(userId);
			list.add(fileImage);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public FileImage uploadImage(FileImageAddParams params) {
		// 查询是否有主图
		FileImage imageOne = new FileImage();
		imageOne.setBizNo(params.getBizNo());
		List<FileImage> imageList = this.getList(imageOne);
		imageOne = imageList.stream().filter(e -> null !=e.getMainLogo() 
				&& FileImage.IS_MAIN_LOGO.intValue() == e.getMainLogo().intValue()).findFirst().orElse(null);
		// 如果没有，将当前图片设为主图
		if (null == imageOne) {
			params.setMainLogo(FileImage.IS_MAIN_LOGO);
		} else {
			params.setMainLogo(FileImage.NOT_MAIN_LOGO);
		}
		
		Long userId = authService.getUserId(params.getToken());
		FileImage fileImage = params.toEntity();
		super.addModel(fileImage,userId);
		return fileImage;
	}

	@Override
	public void changeMainLogo(FileImageUpdateParams params) {
		FileImage fileImage = this.getDetail(params.toEntity());
		AssertUtil.notNull(fileImage, "图片不存在或已删除。");
		
		FileImage imageOne = new FileImage();
		imageOne.setBizNo(fileImage.getBizNo());
		List<FileImage> imageList = this.getList(imageOne);
		imageOne = imageList.stream().filter(e -> null !=e.getMainLogo() 
				&& FileImage.IS_MAIN_LOGO.intValue() == e.getMainLogo().intValue()).findFirst().orElse(null);
		// 将原主图标识取消
		if (null != imageOne) {
			imageOne.setMainLogo(FileImage.NOT_MAIN_LOGO);
			this.updateById(imageOne);
		}
		
		// 设置新主图标识
		fileImage.setMainLogo(FileImage.IS_MAIN_LOGO);
		this.updateById(fileImage);
	}
}

