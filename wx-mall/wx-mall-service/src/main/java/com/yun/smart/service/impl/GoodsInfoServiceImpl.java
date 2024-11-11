package com.yun.smart.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yun.smart.base.BaseServiceImpl;
import com.yun.smart.consts.BillNoConsts;
import com.yun.smart.enums.BooleanValue;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.mapper.GoodsInfoMapper;
import com.yun.smart.model.FileImage;
import com.yun.smart.model.GoodsInfo;
import com.yun.smart.param.FileImageSearchParams;
import com.yun.smart.param.GoodsInfoAddParams;
import com.yun.smart.param.GoodsInfoDeleteParams;
import com.yun.smart.param.GoodsInfoSearchParams;
import com.yun.smart.param.GoodsInfoUpdateParams;
import com.yun.smart.service.FileImageService;
import com.yun.smart.service.GoodsInfoService;
import com.yun.smart.utils.AssertUtil;

/**
 * ServiceImpl - 商品列表
 * @author qihh
 * @version 0.0.1
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl extends BaseServiceImpl<GoodsInfoMapper,GoodsInfo> implements GoodsInfoService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.GOODSINFO);

	@Resource
	private GoodsInfoMapper goodsInfoMapper;
	@Resource
	private FileImageService fileImageService;
	
	@Override
	public Page<Map<String,Object>> searchPage(GoodsInfoSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		page.setOrderByField("a.create_time");
		
		logger.info("GoodsInfoService-分页查询商品列表入参:{}",params);
		List<Map<String,Object>> result = goodsInfoMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<GoodsInfo> searchList(GoodsInfoSearchParams params) {
		return super.getList(params.toEntity());
	}

	@Override
	public GoodsInfo searchDetail(GoodsInfoSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(GoodsInfoAddParams params) {
		Long userId = authService.getUserId(params.getToken());
		GoodsInfo goodsInfo = params.toEntity();
		goodsInfo.setGoodsNo(BillNoConsts.GOODS_NO + IdWorker.getId());
		super.addModel(goodsInfo,userId);
	}

	@Override
	public void update(GoodsInfoUpdateParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(GoodsInfoDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(GoodsInfoDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		GoodsInfo goodsInfo = null;
		List<GoodsInfo> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			goodsInfo = new GoodsInfo();
			goodsInfo.setId(id);
			goodsInfo.setEnable(BooleanValue.FALSE.value());
			goodsInfo.setUpdateTime(updateDate);
			goodsInfo.setUpdateBy(userId);
			list.add(goodsInfo);
		}
		
		super.updateBatchById(list);
	}

	@Override
	public Map<String, Object> searchListApp(GoodsInfoSearchParams params) {
		logger.info("GoodsInfoService-分页查询商品列表入参:{}",params);
		params.setEnable(BooleanValue.TRUE.value());
		params.setSellStatus(GoodsInfo.SELL_STATUS_1);
		List<Map<String,Object>> goodsList = goodsInfoMapper.searchPage(params);
		
		params.setSellStatus(GoodsInfo.SELL_STATUS_4);
		List<Map<String,Object>> salesList = goodsInfoMapper.searchPage(params);
		
		Map<String,Object> result = new HashMap<>();
		result.put("salesList", salesList);//活动款
		result.put("goodsList", goodsList);//在售款
		
		return result;
	}

	@Override
	public Map<String, Object> searchDetailApp(GoodsInfoSearchParams params) {
		logger.info("GoodsInfoService-查询商品明细入参:{}",params);
		GoodsInfo goodsInfo = this.getDetail(params.toEntity());
		AssertUtil.notNull(goodsInfo, "商品不存在或已删除。");
		
		// 查询商品图片
		FileImageSearchParams imageParams = new FileImageSearchParams();
		imageParams.setEnable(BooleanValue.TRUE.value());
		imageParams.setBizNo(goodsInfo.getGoodsNo());
		imageParams.setPage(1);
		imageParams.setPageSize(30);
		Page<Map<String,Object>> pageList = fileImageService.searchPage(imageParams);
		
		// 查询顶部展示图片
		FileImage fileImage = new FileImage();
		fileImage.setBizNo(goodsInfo.getGoodsNo());
		fileImage.setTopShow(FileImage.IS_TOP_SHOW);
		List<FileImage> showList = fileImageService.getList(fileImage);
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("goodsInfo", goodsInfo);
		result.put("imageList", pageList.getRecords());
		result.put("showList", showList);
		return result;
	}

	@Override
	public List<Map<String, Object>> searchListCategory(GoodsInfoSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		logger.info("GoodsInfoService-查询产品信息分类列表入参:{}",params);
		List<Map<String,Object>> goodsList = goodsInfoMapper.searchPage(params);
		return goodsList;
	}
}

