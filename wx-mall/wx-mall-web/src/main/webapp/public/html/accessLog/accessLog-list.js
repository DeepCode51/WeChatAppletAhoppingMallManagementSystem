var AccessLog = new Object();

/**
 * 行数据格式化
 */
AccessLog.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 行操作事件
 */
AccessLog.operateEvents = {
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'accessLog', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
AccessLog.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		AccessLog.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/accessLog-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('accessLog', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
AccessLog.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['accessLog']['searchPage']);
};



$(document).ready(function () {
	AccessLog.loadTableData(); //加载table数据
	AccessLog.bindEnvent(); //绑定按钮事件
});
