var GoodsInfoImage = new Object();
	
GoodsInfoImage.initPage = function () {
	// 查询商品图片
	var bizNo = $.getUrlParam('goodsNo');
	var goodsName = $.getUrlParam('name');
	GoodsInfoImage.initGoodsImages(bizNo);
	
	var params = {
		url: '/smart/fileImage/pc/v1/uploadImage',
		args: {
			'bizType': '1',//类型：1=商品图 2=评论图 3=售后图
			'bizNo': bizNo,
			'fileName': goodsName//商品名称
		},
		callback: function(){
			GoodsInfoImage.initGoodsImages(bizNo);
		}
	}
	
	// 初始化图片插件
	WebuploaderFile.initWebuploader(params);
}

/**
 * 查询商品图片并渲染
 */
GoodsInfoImage.initGoodsImages = function (bizNo) {
	$('#goods-images').html('');
	AjaxUtils.sendAjaxRequest({"bizNo": bizNo},'fileImage','searchList','POST').then(function(res){
		var imageList = res.data;
		imageList.forEach(function(item) {
			var imageHtml = $('<li><div onmouseover="show(this)" onmouseout="hide(this)">'
					+'<a href="#"><img src="'+item.filePath+'"></a>'
					+'<div class="op-btn"><button onClick="GoodsInfoImage.delImage('+item.id+',this);" class="btn btn-danger radius" type="button">&nbsp;&nbsp;删除&nbsp;&nbsp;</button>'
					+'</div></div></li>');
			$('#goods-images').append(imageHtml);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
}

/**
 * 删除图片
 */
GoodsInfoImage.delImage = function (id, obj) {
	layer.confirm('确认要删除吗？',function(){
		AjaxUtils.sendAjaxRequest({"id": id},'fileImage','deleteById','POST').then(function (res) {
			layer.msg('已删除!',{icon:1,time:1000},function(){
				// 从列表中移除
				$(obj).parent().parent().remove();
			});
		},function (err) {
			layer.msg(err,{icon:5,time:1000});
		});
	});
}

/**
 * 设置图片为主图
 */
GoodsInfoImage.setMain = function (id) {
	layer.confirm('确认要删除吗？',function(){
		AjaxUtils.sendAjaxRequest({"id": id,"mainLogo":"1"},'fileImage','update','POST').then(function (res) {
			layer.msg('已设置!',{icon:1,time:1000});
		},function (err) {
			layer.msg(err,{icon:5,time:1000});
		});
	});
}

function show(thisObj) {
	$(thisObj).children("div").css("display", "block");
}

function hide(thisObj) {
	$(thisObj).children("div").css("display", "none");
}
	
$(document).ready(function () {
	GoodsInfoImage.initPage();
});