var GoodsInfo = new Object();

/**
 * 行数据格式化
 */
GoodsInfo.operateFormatter = function (value, row, index) {
	var btns = [
		'<a title="编辑" class="btn btn-primary radius row_edit"><i class="Hui-iconfont">&#xe60c;</i></a>',
		'<a title="查看图片" class="btn btn-secondary radius row_image"><i class="Hui-iconfont">&#xe685;</i></a>',
		'<a title="删除" class="btn btn-danger radius row_delete"><i class="Hui-iconfont">&#xe6e2;</i></a>'
	];
	return btns.join("&nbsp;"); 
};

/**
 * 商品类型格式化
 */
GoodsInfo.goodsTypeFormatter = function (value, row, index) {
	if (value) {
		return CommUtils.cmFormatDictionary(DicInfoConsts.getGoodsType(), value); 
	}
	return value;
};

/**
 * 商品状态格式化
 */
GoodsInfo.sellStatusFormatter = function (value, row, index) {
	if (value) {
		return CommUtils.cmFormatDictionary(DicInfoConsts.getSellStatus(), value); 
	}
	return value;
};

/**
 * 价格格式化
 */
GoodsInfo.priceFormatter = function (value, row, index) {
	if (value) {
		return value + "元"; 
	}
	return value;
};

/**
 * 数量格式化
 */
GoodsInfo.quatityFormatter = function (value, row, index) {
	if (value) {
		return value + row.weightUnit; 
	}
	return value;
};

/**
 * 图片格式化
 */
GoodsInfo.mainLogoFormatter = function (value, row, index) {
	if (value) {
		return '<a href="'+value+'"><img class="thumbnail" src="'+value+'" width="100px;" height="100px;"></img></a>'; 
	}
	return value;
};

/**
 * 行操作事件
 */
GoodsInfo.operateEvents = {
	'click .row_image': function (e, value, row) {
		CommUtils.cmOpenIframe("查看图片","image/goodsInfo-image.html?goodsNo="+row.goodsNo+"&name="+row.name);
	},
	'click .row_edit': function (e, value, row) {
		CommUtils.cmOpenIframe("编辑","edit/goodsInfo-edit.html?id="+row.id);
	},
	'click .row_delete': function (e, value, row) {
		CommUtils.cmDeleteRow(row.id, 'goodsInfo', $('#table_list'));
	}
};

/** 
 * 绑定事件
 */
GoodsInfo.bindEnvent = function () {
	// 按条件搜索
	$('#table_search').on('click',function () {
		GoodsInfo.loadTableData();
	});
	
	// 新增
	$('#table_add').on('click',function () {
		CommUtils.cmOpenIframe("新增","add/goodsInfo-add.html");
	});
	
	// 批量删除
	$('#table_delete').on('click',function () {
		CommUtils.cmDeleteRows('goodsInfo', $('#table_list'));
	});
};

/**
 * 加载数据表格
 */
GoodsInfo.loadTableData = function () {
	CommUtils.cmInitTable($('#table_list'),ajaxUrl['goodsInfo']['searchPage']);
};

GoodsInfo.initPage = function () {
	$('select[name="goodsType"]').select2({data: DicInfoConsts.getGoodsType()});
	$('select[name="sellStatus"]').select2({data: DicInfoConsts.getSellStatus()});
}

$(document).ready(function () {
	GoodsInfo.initPage();
	GoodsInfo.loadTableData(); //加载table数据
	GoodsInfo.bindEnvent(); //绑定按钮事件
});
