var DicInfoAdd = new Object();
	
// 保存表单
DicInfoAdd.saveDicInfo = function(form) {
	var params = $("#form-dicInfo-add").serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'dicInfo','add','POST').then(function(res){
		layer.msg('已保存!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

DicInfoAdd.loadDicData = function () {
	var params = {"parentId":"0"};
	AjaxUtils.sendAjaxRequest(params,'dicInfo','searchList','POST').then(function(res){
		var parentData = [{"id":"","text":"请选择"}];
		res.data.forEach(function(item){
			parentData.push({
				"id": item.id,
				"text": item.dicText
			});
		});
		$('#parentId').select2({data: parentData, width:'270px'});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
}

	
$(document).ready(function () {
	DicInfoAdd.loadDicData();
	CommUtils.cmInitForm($("#form-dicInfo-add"), DicInfoAdd.saveDicInfo); //初始化表单校验规则
	
});