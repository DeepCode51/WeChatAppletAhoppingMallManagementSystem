var FileTipsEdit = new Object();
    
// 渲染表单数据
FileTipsEdit.fillFormData = function () {
	var id = $.getUrlParam('id');
	AjaxUtils.sendAjaxRequest({"id": id},'fileTips','searchDetail','POST').then(function(res){
		var fileTipsRow = res.data;
		$('#form-fileTips-edit').find('input[name]').each(function () {
			$(this).val(fileTipsRow[$(this).attr('name')]);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

// 更新表单
FileTipsEdit.updateFileTips = function(form) {
	var params = $('#form-fileTips-edit').serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'fileTips','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

$(document).ready(function () {
	FileTipsEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-fileTips-edit'), FileTipsEdit.updateFileTips); //初始化表单校验规则
});