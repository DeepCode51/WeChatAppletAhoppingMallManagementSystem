var OrderNotice = new Object();

/**
 * 行数据格式化
 */
OrderNotice.operateFormatter = function (value, row, index) {
	var edit1 = '<a title="设为已读" class="btn btn-success radius row_edit"><i class="Hui-iconfont">&#xe676;</i></a>';
	var edit2 = '<a title="设为已读" class="btn btn-disabled radius" disabled="disabled"><i class="Hui-iconfont">&#xe676;</i></a>';
	var del = '<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>';
	var btns = [];
	if (row.noticeStatus != '2') {
		btns.push(edit1);
	} else {
		btns.push(edit2);
	}
	btns.push(del);
	return btns.join("&nbsp;"); 
};

/**
 * 通知类型格式化
 */
OrderNotice.noticeTypeFormatter = function (value, row, index) {
	if (value) {
		var text = CommUtils.cmFormatDictionary(DicInfoConsts.getNoticeType(), value);
		// 消息未读，添加样式
		if (row.noticeStatus == '1') {
			// 待发货
			if (value == '1') {
				return '<div class="div-flag div-flag-green"><i class="Hui-iconfont">'+text+'</i></div>';
			}
			// 申请售后
			return '<div class="div-flag div-flag-red"><i class="Hui-iconfont">'+text+'</i></div>';
			
		} else {
			return '<div class="div-flag div-flag-null"><i class="Hui-iconfont">'+text+'</i></div>';
		}
	}
	return value;
};

/**
 * 处理状态格式化
 */
OrderNotice.noticeStatusFormatter = function (value, row, index) {
	if (value) {
		var text = CommUtils.cmFormatDictionary(DicInfoConsts.getNoticeStatus(), value);
		// 消息未读
		if (value == '1') {
			return '<div class="div-flag div-flag-warn"><i class="Hui-iconfont">'+text+'</i></div>';
		}
		// 消息已读
		return '<div class="div-flag div-flag-null"><i class="Hui-iconfont">'+text+'</i></div>';  
	}
	return value;
};

/**
 * 行操作事件
 */
OrderNotice.operateEvents = {
	'click .row_edit': function (e, value, row) {
		// 设置消息已读
		AjaxUtils.sendAjaxRequest({"id": row.id},'orderNotice','changeNoticeStatus','POST').then(function(res){
			layer.msg('设置成功！',{icon:1,time:1000});
			// 重新加载表格
			OrderNotice.loadTableData();
		},function(err){
			layer.msg(err,{icon:5,time:1000});
		});
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'orderNotice', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
OrderNotice.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		OrderNotice.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/orderNotice-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('orderNotice', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
OrderNotice.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['orderNotice']['searchPage']);
};

OrderNotice.initPage = function () {
	$('select[name="noticeType"]').select2({data: DicInfoConsts.getNoticeType()});
	$('select[name="noticeStatus"]').select2({data: DicInfoConsts.getNoticeStatus()});
};

$(document).ready(function () {
	OrderNotice.initPage();
	OrderNotice.loadTableData(); //加载table数据
	OrderNotice.bindEnvent(); //绑定按钮事件
});
