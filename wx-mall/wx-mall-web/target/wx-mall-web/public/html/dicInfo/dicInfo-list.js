var DicInfo = new Object();

/**
 * 行数据格式化
 */
DicInfo.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="编辑" class="btn btn-primary radius row_edit"><i class="Hui-iconfont">&#xe60c;</i></a>',
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 行操作事件
 */
DicInfo.operateEvents = {
	'click .row_edit': function (e, value, row) {
		CommUtils.cmOpenIframe("编辑","edit/dicInfo-edit.html?id="+row.id);
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'dicInfo', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
DicInfo.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		DicInfo.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/dicInfo-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('dicInfo', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
DicInfo.loadTableData = function () {
	var params = {};
	var formData = $('#table_search_form').serializeArray();
	$.each(formData, function(i, field){
		if ($.trim(field.value) != '') {
			params[field.name] = field.value;
		}
	});
	
	AjaxUtils.sendAjaxRequest(params,'dicInfo','searchList','POST').then(function (res) {
		$('#table_list').bootstrapTable('destroy').bootstrapTable({
			data: res.data,
			treeView: true,
            treeId: "id",
            treeField: "dicText",
		});
	},function (err) {
		layer.msg(err,{icon: 5,time:1000});
    });
};



$(document).ready(function () {
	DicInfo.loadTableData(); //加载table数据
	DicInfo.bindEnvent(); //绑定按钮事件
});
