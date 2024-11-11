var OrderInfo = new Object();

/**
 * 行数据格式化
 */
OrderInfo.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="查看详情" class="btn btn-secondary radius row_edit"><i class="Hui-iconfont">&#xe685;</i></a>',
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 价格格式化
 */
OrderInfo.priceFormatter = function (value, row, index) {
	if (value) {
		return value + "元"; 
	}
	return value;
};

/**
 * 订单状态格式化
 */
OrderInfo.orderStatusFormatter = function (value, row, index) {
	if (value) {
		return CommUtils.cmFormatDictionary(DicInfoConsts.getOrderStatus(), value); 
	}
	return value;
}

/**
 * 行操作事件
 */
OrderInfo.operateEvents = {
	'click .row_edit': function (e, value, row) {
		CommUtils.cmOpenIframe("编辑","edit/orderInfo-edit.html?orderNo="+row.orderNo);
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'orderInfo', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
OrderInfo.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		OrderInfo.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/orderInfo-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('orderInfo', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
OrderInfo.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['orderInfo']['searchPage']);
};

OrderInfo.initPage = function () {
	$('select[name="orderStatus"]').select2({data: DicInfoConsts.getOrderStatus()});
	CommUtils.cmInitDate('#minCreateTime');
	CommUtils.cmInitDate('#maxCreateTime');
}


$(document).ready(function () {
	OrderInfo.initPage();
	OrderInfo.loadTableData(); //加载table数据
	OrderInfo.bindEnvent(); //绑定按钮事件
});
