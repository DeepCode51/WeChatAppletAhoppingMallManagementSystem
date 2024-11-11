var FileTips = new Object();

/**
 * 行数据格式化
 */
FileTips.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="编辑" class="btn btn-primary radius row_edit"><i class="Hui-iconfont">&#xe60c;</i></a>',
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 行操作事件
 */
FileTips.operateEvents = {
	'click .row_edit': function (e, value, row) {
		CommUtils.cmOpenIframe("编辑","edit/fileTips-edit.html?id="+row.id);
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'fileTips', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
FileTips.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		FileTips.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/fileTips-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('fileTips', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
FileTips.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['fileTips']['searchPage']);
};



$(document).ready(function () {
	FileTips.loadTableData(); //加载table数据
	FileTips.bindEnvent(); //绑定按钮事件
});
