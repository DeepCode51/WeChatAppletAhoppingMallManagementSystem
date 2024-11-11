package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.GoodsInfo;
import com.yun.smart.param.GoodsInfoAddParams;
import com.yun.smart.param.GoodsInfoDeleteParams;
import com.yun.smart.param.GoodsInfoSearchParams;
import com.yun.smart.param.GoodsInfoUpdateParams;

/**
 * Service - 产品信息
 * @author qihh
 * @version 0.0.1
 */
public interface GoodsInfoService extends BaseService<GoodsInfo> {

	/**
	 * 分页查询
	 * @param GoodsInfoSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(GoodsInfoSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param GoodsInfoSearchParams
	 * @return List<GoodsInfo>
	 */
	List<GoodsInfo> searchList(GoodsInfoSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param GoodsInfoSearchParams
	 * @return GoodsInfo
	 */
	GoodsInfo searchDetail(GoodsInfoSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param GoodsInfoAddParams
	 */
	void add(GoodsInfoAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param GoodsInfoUpdateParams
	 */
	void update(GoodsInfoUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param GoodsInfoDeleteParams
	 */
	void delete(GoodsInfoDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(GoodsInfoDeleteParams params);

	/**
	 * app-查询商品首页列表
	 * 状态：1=有货 2=无货 3=下架(历史商品) 4=活动
	 * @param params
	 * @return
	 */
	Map<String,Object> searchListApp(GoodsInfoSearchParams params);

	/**
	 * app-查询商品明细 - 包括商品图(最新的30张图片)
	 * @param params
	 * @return
	 */
	Map<String, Object> searchDetailApp(GoodsInfoSearchParams params);

	/**
	 * app-查询产品信息分类列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> searchListCategory(GoodsInfoSearchParams params);

}
