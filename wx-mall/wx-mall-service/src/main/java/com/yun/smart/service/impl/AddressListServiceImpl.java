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
import com.yun.smart.mapper.AddressListMapper;
import com.yun.smart.model.AddressList;
import com.yun.smart.param.AddressListAddParams;
import com.yun.smart.param.AddressListDeleteParams;
import com.yun.smart.param.AddressListSearchParams;
import com.yun.smart.param.AddressListUpdateParams;
import com.yun.smart.service.AddressListService;

/**
 * ServiceImpl - 地址列表
 * @author qihh
 * @version 0.0.1
 */
@Service("addressListService")
public class AddressListServiceImpl extends BaseServiceImpl<AddressListMapper,AddressList> implements AddressListService {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.ADDRESSLIST);

	@Resource
	private AddressListMapper addressListMapper;
	
	@Override
	public Page<Map<String,Object>> searchPage(AddressListSearchParams params) {
		params.setEnable(BooleanValue.TRUE.value());
		Page<Map<String,Object>> page = super.getPageHelper(params);
		page.setOrderByField("a.create_time");
		
		logger.info("AddressListService-分页查询地址列表入参:{}",params);
		List<Map<String,Object>> result = addressListMapper.searchPage(page,params);
		page.setRecords(result);
		return page;
	}

	@Override
	public List<AddressList> searchList(AddressListSearchParams params) {
		Long userId = authService.getUserId(params.getToken());
		params.setUserId(userId);
		logger.info("AddressListService-查询个人地址列表入参:{}",params);
		return super.getList(params.toEntity());
	}

	@Override
	public AddressList searchDetail(AddressListSearchParams params) {
		return super.getDetail(params.toEntity());
	}

	@Override
	public void add(AddressListAddParams params) {
		logger.info("AddressListService-添加联系地址入参:{}",params);
		Long userId = authService.getUserId(params.getToken());
		// 查询是否有默认地址
		AddressList addressOne = new AddressList();
		addressOne.setUserId(userId);
		List<AddressList> addressList = this.getList(addressOne);
		addressOne = addressList.stream().filter(e -> null !=e.getDefaultAddr() 
				&& AddressList.IS_DEFAULT_ADDR.intValue() == e.getDefaultAddr().intValue()).findFirst().orElse(null);
		// 如果没有，将当前地址设为默认
		if (null == addressOne) {
			params.setDefaultAddr(AddressList.IS_DEFAULT_ADDR);
		} else {
			params.setDefaultAddr(AddressList.NOT_DEFAULT_ADDR);
		}
		
		AddressList address = params.toEntity();
		address.setUserId(userId);
		super.addModel(address, userId);
	}

	@Override
	public void update(AddressListUpdateParams params) {
		logger.info("AddressListService-更新联系地址入参:{}",params);
		Long userId = authService.getUserId(params.getToken());
		// 查询是否有默认地址
		AddressList addressOne = new AddressList();
		addressOne.setUserId(userId);
		List<AddressList> addressList = this.getList(addressOne);
		addressOne = addressList.stream().filter(e -> null !=e.getDefaultAddr() 
				&& AddressList.IS_DEFAULT_ADDR.intValue() == e.getDefaultAddr().intValue()).findFirst().orElse(null);
		// 如果设置当前地址为默认地址，需要将已有的默认地址取消设置
		if (null != addressOne) {
			if (null != params.getDefaultAddr() 
					&& AddressList.IS_DEFAULT_ADDR.intValue() == params.getDefaultAddr().intValue()) {
				addressOne.setDefaultAddr(AddressList.NOT_DEFAULT_ADDR);
				this.updateById(addressOne);
			}
		}
		
		super.updateModel(params.toEntity(),userId);
	}

	@Override
	public void delete(AddressListDeleteParams params) {
		Long userId = authService.getUserId(params.getToken());
		super.deleteModel(params.toEntity(),userId);
	}
	
	@Override
	public void deleteByIds(AddressListDeleteParams params) {
		Date updateDate = new Date();
		Long userId = authService.getUserId(params.getToken());
		AddressList addressList = null;
		List<AddressList> list = Lists.newArrayList();
		for (Long id : params.getIds()) {
			addressList = new AddressList();
			addressList.setId(id);
			addressList.setEnable(BooleanValue.FALSE.value());
			addressList.setUpdateTime(updateDate);
			addressList.setUpdateBy(userId);
			list.add(addressList);
		}
		
		super.updateBatchById(list);
	}
}

