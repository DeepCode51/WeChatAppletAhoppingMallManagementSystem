var GoodsInfoEdit = new Object();
    
// 渲染表单数据
GoodsInfoEdit.fillFormData = function () {
	var id = $.getUrlParam('id');
	AjaxUtils.sendAjaxRequest({"id": id},'goodsInfo','searchDetail','POST').then(function(res){
		var goodsInfoRow = res.data;
		$('#form-goodsInfo-edit').find('input[name]').each(function () {
			$(this).val(goodsInfoRow[$(this).attr('name')]);
		});
		$('textarea[name="beforeSell"]').val(goodsInfoRow.beforeSell);
		$('textarea[name="afterSell"]').val(goodsInfoRow.afterSell);
		
		$('select[name="goodsType"]').val(goodsInfoRow.goodsType);
		$('select[name="sellStatus"]').val(goodsInfoRow.sellStatus);
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

// 更新表单
GoodsInfoEdit.updateGoodsInfo = function(form) {
	var params = $('#form-goodsInfo-edit').serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'goodsInfo','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

GoodsInfoEdit.initPage = function () {
	$('select[name="goodsType"]').select2({data: DicInfoConsts.getGoodsType()});
	$('select[name="sellStatus"]').select2({data: DicInfoConsts.getSellStatus()});
}

$(document).ready(function () {
	GoodsInfoEdit.initPage();
	GoodsInfoEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-goodsInfo-edit'), GoodsInfoEdit.updateGoodsInfo); //初始化表单校验规则
});