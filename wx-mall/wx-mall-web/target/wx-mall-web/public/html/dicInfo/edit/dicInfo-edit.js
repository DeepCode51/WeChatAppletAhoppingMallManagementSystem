var DicInfoEdit = new Object();
    
// 渲染表单数据
DicInfoEdit.fillFormData = function () {
	var id = $.getUrlParam('id');
	AjaxUtils.sendAjaxRequest({"id": id},'dicInfo','searchDetail','POST').then(function(res){
		var dicInfoRow = res.data;
		$('#form-dicInfo-edit').find('input[name]').each(function () {
			$(this).val(dicInfoRow[$(this).attr('name')]);
		});
		DicInfoEdit.loadDicData(dicInfoRow.parentId);
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

DicInfoEdit.loadDicData = function (parentId) {
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
		$('#parentId').val(parentId).select2();
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
}

// 更新表单
DicInfoEdit.updateDicInfo = function(form) {
	var params = $('#form-dicInfo-edit').serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'dicInfo','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

$(document).ready(function () {
	DicInfoEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-dicInfo-edit'), DicInfoEdit.updateDicInfo); //初始化表单校验规则
});