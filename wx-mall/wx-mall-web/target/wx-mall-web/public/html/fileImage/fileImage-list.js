var FileImage = new Object();

/**
 * 行数据格式化
 */
FileImage.operateFormatter = function (value, row, index) {
	var edit1 = '<a title="设为主图" class="btn btn-primary radius row_edit"><i class="Hui-iconfont">&#xe61d;</i></a>';
	var edit2 = '<a title="设为主图" class="btn btn-disabled radius" disabled="disabled"><i class="Hui-iconfont">&#xe61d;</i></a>';
	var show = '<a title="设为展示图" class="btn btn-success radius row_show"><i class="Hui-iconfont">&#xe61d;</i></a>';
	var del = '<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>';
	var btns = [];
	if (row.mainLogo != '1') {
		btns.push(edit1);
	} else {
		btns.push(edit2);
	}
	btns.push(show);
	btns.push(del);
	return btns.join("&nbsp;"); 
};

/**
 * 行操作事件
 */
FileImage.operateEvents = {
	'click .row_edit': function (e, value, row) {
		// 设置主图
		AjaxUtils.sendAjaxRequest({"id": row.id},'fileImage','changeMainLogo','POST').then(function(res){
			layer.msg('设置成功！',{icon:1,time:1000});
			// 重新加载表格
			FileImage.loadTableData();
		},function(err){
			layer.msg(err,{icon:5,time:1000});
		});
	},
	'click .row_show': function (e, value, row) {
		// 设置展示图
		var topShow = '';
		if (row.topShow == '1') {
			topShow = '0';
		} else {
			topShow = '1';
		}
		AjaxUtils.sendAjaxRequest({"id": row.id,"topShow":topShow},'fileImage','updateById','POST').then(function(res){
			layer.msg('设置成功！',{icon:1,time:1000});
			// 重新加载表格
			FileImage.loadTableData();
		},function(err){
			layer.msg(err,{icon:5,time:1000});
		});
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'fileImage', $('#table_list'));
	}
};

/**
 * 图片格式化
 */
FileImage.imageFormatter = function (value, row, index) {
	if (value) {
		return '<a href="'+value+'"><img class="thumbnail" src="'+value+'" width="100px;" height="100px;"></img></a>'; 
	}
	return value;
};

/**
 * 图片类型格式化
 */
FileImage.bizTypeFormatter = function (value, row, index) {
	if (value) {
		return CommUtils.cmFormatDictionary(DicInfoConsts.getImageBizType(), value); 
	}
	return value;
};

/**
 * 主图类型格式化
 */
FileImage.mainLogoFormatter = function (value, row, index) {
	if (value && value == '1') {
		return '<div class="div-flag div-flag-red"><i class="Hui-iconfont">&#xe69e;</i></div>';
	}
	return '';
};

/**
 * 展示图类型格式化
 */
FileImage.topShowFormatter = function (value, row, index) {
	if (value && value == '1') {
		return '<div class="div-flag div-flag-warn"><i class="Hui-iconfont">&#xe69e;</i></div>';
	}
	return '';
};

/** 
 * 绑定事件
 */
FileImage.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		FileImage.loadTableData();
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('fileImage', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
FileImage.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['fileImage']['searchPage']);
};



$(document).ready(function () {
	$('select[name="bizType"]').select2({data: DicInfoConsts.getImageBizType()});
	FileImage.loadTableData(); //加载table数据
	FileImage.bindEnvent(); //绑定按钮事件
});
