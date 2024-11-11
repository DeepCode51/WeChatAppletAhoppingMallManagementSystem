package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.OrderComment;
import com.yun.smart.param.OrderCommentAddParams;
import com.yun.smart.param.OrderCommentDeleteParams;
import com.yun.smart.param.OrderCommentResponseParams;
import com.yun.smart.param.OrderCommentSearchParams;
import com.yun.smart.param.OrderCommentUpdateParams;

/**
 * Service - 评论
 * @author qihh
 * @version 0.0.1
 */
public interface OrderCommentService extends BaseService<OrderComment> {

	/**
	 * 分页查询
	 * @param OrderCommentSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(OrderCommentSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param OrderCommentSearchParams
	 * @return List<OrderComment>
	 */
	List<OrderComment> searchList(OrderCommentSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param OrderCommentSearchParams
	 * @return OrderComment
	 */
	OrderComment searchDetail(OrderCommentSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param OrderCommentAddParams
	 */
	void add(OrderCommentAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param OrderCommentUpdateParams
	 */
	void update(OrderCommentUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param OrderCommentDeleteParams
	 */
	void delete(OrderCommentDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(OrderCommentDeleteParams params);

	/**
	 * 查询评论详情，评论图片
	 * @param params
	 * @return
	 */
	Map<String, Object> searchInfo(OrderCommentSearchParams params);

	/**
	 * 批量回复评论
	 * @param params
	 */
	void batchResponse(OrderCommentResponseParams params);

}
