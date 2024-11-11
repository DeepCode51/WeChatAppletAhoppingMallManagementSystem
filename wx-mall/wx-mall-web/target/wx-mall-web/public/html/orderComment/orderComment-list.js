var OrderComment = new Object();

/**
 * 行数据格式化
 */
OrderComment.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="查看评论" class="btn btn-secondary radius row_view"><i class="Hui-iconfont">&#xe685;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 评分等级格式化
 */
OrderComment.goodsStarFormatter = function (value, row, index) {
	if (value) {
		var star_css = 'star-' + value;
		var starHtml = '<div class="formControls col-xs-8 col-sm-9">' +
			'<div class="star-bar star-bar-show size-S f-l va-m mr-10">' +
			'<span class="star '+ star_css +'"></span>' +
			'</div>' +
			'<strong class="f-l f-14 va-m">'+ value +'</strong>' +
			'</div>';
		
		return starHtml;
	}
	return value;
};

/**
 * 类型格式化
 */
OrderComment.commentTypeFormatter = function (value, row, index) {
	if (value) {
		var text = CommUtils.cmFormatDictionary(DicInfoConsts.getCommentType(), value);
		// 评论
		if (value == '1') {
			return '<div class="div-flag div-flag-green"><i class="Hui-iconfont">'+text+'</i></div>';
		}
		// 售后
		return '<div class="div-flag div-flag-red"><i class="Hui-iconfont">'+text+'</i></div>';  
	}
	return value;
};

/**
 * 行操作事件
 */
OrderComment.operateEvents = {
	'click .row_view': function (e, value, row) {
		CommUtils.cmOpenIframe("查看评论","edit/orderComment-edit.html?id="+row.id);
	}
};

/** 
 * 绑定事件
 */
OrderComment.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		OrderComment.loadTableData();
	});
	
	// 批量回复
	$('#table_response').on('click',function () {
		var rows = $('#table_list').bootstrapTable('getSelections');
		if (rows.length == 0) {
			layer.msg('请至少选择一项进行回复！',{icon:5,time:1000});
			return;
		}
		var ids = [];
		rows.forEach(function(item){
			ids.push(item.id);
		});
		
		layer.prompt({
		  formType: 2,
		  value: '感谢亲亲的好评，您的满意是我们最大的动力。',
		  title: '请输入回复内容：',
		}, function(value, index, elem){
			var params = {"ids": ids, "responseContent": value};
			AjaxUtils.sendAjaxRequest(params, 'orderComment', 'batchResponse','POST').then(function (res) {
				// 重新加载table
				OrderComment.loadTableData();
				layer.close(index);
			},function (err) {
				layer.msg(err,{icon:5,time:1000});
			});
		});
	});
};

/**
 * 加载数据表格
 */
OrderComment.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['orderComment']['searchPage']);
};

OrderComment.initPage = function () {
	$('select[name="goodsStar"]').select2({data: DicInfoConsts.getGoodsStar()});
	$('select[name="commentType"]').select2({data: DicInfoConsts.getCommentType()});
	CommUtils.cmInitDate('#minCreateTime');
	CommUtils.cmInitDate('#maxCreateTime');
}

$(document).ready(function () {
	OrderComment.initPage();
	OrderComment.loadTableData(); //加载table数据
	OrderComment.bindEnvent(); //绑定按钮事件
});
