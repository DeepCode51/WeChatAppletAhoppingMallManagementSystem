var UserInfo = new Object();

/**
 * 行数据格式化
 */
UserInfo.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="编辑" class="btn btn-primary radius row_edit"><i class="Hui-iconfont">&#xe60c;</i></a>',
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 商品类型格式化
 */
UserInfo.userTypeFormatter = function (value, row, index) {
	if (value) {
		return CommUtils.cmFormatDictionary(DicInfoConsts.getUserType(), value); 
	}
	return value;
};


/**
 * 行操作事件
 */
UserInfo.operateEvents = {
	'click .row_edit': function (e, value, row) {
		CommUtils.cmOpenIframe("编辑","edit/userInfo-edit.html?id="+row.id);
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'userInfo', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
UserInfo.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		UserInfo.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/userInfo-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('userInfo', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
UserInfo.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['userInfo']['searchPage']);
};



$(document).ready(function () {
	$('select[name="userType"]').select2({data: DicInfoConsts.getUserType()});
	UserInfo.loadTableData(); //加载table数据
	UserInfo.bindEnvent(); //绑定按钮事件
});
