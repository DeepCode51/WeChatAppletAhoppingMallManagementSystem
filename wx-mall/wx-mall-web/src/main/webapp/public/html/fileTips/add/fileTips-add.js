var FileTipsAdd = new Object();
	
// 保存表单
FileTipsAdd.saveFileTips = function(form) {
	var params = $("#form-fileTips-add").serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'fileTips','add','POST').then(function(res){
		layer.msg('已保存!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};
	
$(document).ready(function () {
	CommUtils.cmInitForm($("#form-fileTips-add"), FileTipsAdd.saveFileTips); //初始化表单校验规则
	
});