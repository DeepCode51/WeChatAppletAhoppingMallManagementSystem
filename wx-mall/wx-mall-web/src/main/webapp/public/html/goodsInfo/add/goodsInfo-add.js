var GoodsInfoAdd = new Object();
	
// 保存表单
GoodsInfoAdd.saveGoodsInfoAdd = function(form) {
	var params = $("#form-goodsInfo-add").serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'goodsInfo','add','POST').then(function(res){
		layer.msg('已保存!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

GoodsInfoAdd.initPage = function () {
	$('select[name="goodsType"]').select2({data: DicInfoConsts.getGoodsType()});
	$('select[name="sellStatus"]').select2({data: DicInfoConsts.getSellStatus()});
}
	
$(document).ready(function () {
	GoodsInfoAdd.initPage();
	CommUtils.cmInitForm($("#form-goodsInfo-add"), GoodsInfoAdd.saveGoodsInfoAdd); //初始化表单校验规则
	
});