var AddressList = new Object();

/**
 * 行数据格式化
 */
AddressList.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="编辑" class="btn btn-primary radius row_edit"><i class="Hui-iconfont">&#xe60c;</i></a>',
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 默认地址格式化
 */
AddressList.defaultAddrFormatter = function (value, row, index) {
	if (value && value == '1') {
		return '<div class="div-flag div-flag-warn"><i class="Hui-iconfont">&#xe69e;</i></div>';
	}
	return '';
};

/**
 * 行操作事件
 */
AddressList.operateEvents = {
	'click .row_edit': function (e, value, row) {
		CommUtils.cmOpenIframe("编辑","edit/addressList-edit.html?id="+row.id);
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'addressList', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
AddressList.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		AddressList.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/addressList-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('addressList', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
AddressList.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['addressList']['searchPage']);
};



$(document).ready(function () {
	AddressList.loadTableData(); //加载table数据
	AddressList.bindEnvent(); //绑定按钮事件
});
