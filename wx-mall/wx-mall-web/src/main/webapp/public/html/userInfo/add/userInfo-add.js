var UserInfoAdd = new Object();
	
// 保存表单
UserInfoAdd.saveUserInfo = function(form) {
	var params = $("#form-userInfo-add").serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'userInfo','add','POST').then(function(res){
		layer.msg('已保存!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};
	
$(document).ready(function () {
	$('select[name="userType"]').select2({data: DicInfoConsts.getUserType()});
	CommUtils.cmInitForm($("#form-userInfo-add"), UserInfoAdd.saveUserInfo); //初始化表单校验规则
});