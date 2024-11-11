package com.yun.smart.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import com.yun.smart.base.BaseService;
import com.yun.smart.model.AddressList;
import com.yun.smart.param.AddressListAddParams;
import com.yun.smart.param.AddressListDeleteParams;
import com.yun.smart.param.AddressListSearchParams;
import com.yun.smart.param.AddressListUpdateParams;

/**
 * Service - 地址
 * @author qihh
 * @version 0.0.1
 */
public interface AddressListService extends BaseService<AddressList> {

	/**
	 * 分页查询
	 * @param AddressListSearchParams
	 * @return Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> searchPage(AddressListSearchParams params);

	/**
	 * 查询列表-自定义参数
	 * @param AddressListSearchParams
	 * @return List<AddressList>
	 */
	List<AddressList> searchList(AddressListSearchParams params);

	/**
	 * 查询详情-自定义参数
	 * @param AddressListSearchParams
	 * @return AddressList
	 */
	AddressList searchDetail(AddressListSearchParams params);

	/**
	 * 添加数据-自定义参数
	 * @param AddressListAddParams
	 */
	void add(AddressListAddParams params);

	/**
	 * 修改数据-自定义参数
	 * @param AddressListUpdateParams
	 */
	void update(AddressListUpdateParams params);

	/**
	 * 删除数据-自定义参数
	 * @param AddressListDeleteParams
	 */
	void delete(AddressListDeleteParams params);
	
	/**
	 * 批量删除数据-自定义参数
	 * @param TestCodeDeleteParams
	 */
	void deleteByIds(AddressListDeleteParams params);

}
