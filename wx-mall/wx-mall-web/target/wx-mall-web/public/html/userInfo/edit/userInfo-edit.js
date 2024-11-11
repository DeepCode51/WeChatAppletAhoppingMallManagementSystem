var UserInfoEdit = new Object();
    
// 渲染表单数据
UserInfoEdit.fillFormData = function () {
	var id = $.getUrlParam('id');
	AjaxUtils.sendAjaxRequest({"id": id},'userInfo','searchDetail','POST').then(function(res){
		var userInfoRow = res.data;
		$('#form-userInfo-edit').find('input[name]').each(function () {
			$(this).val(userInfoRow[$(this).attr('name')]);
		});
		DicInfoConsts.setUserType('select[name="userType"]', userInfoRow.userType);
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

// 更新表单
UserInfoEdit.updateUserInfo = function(form) {
	var params = $('#form-userInfo-edit').serializeObject();
	
	AjaxUtils.sendAjaxRequest(params,'userInfo','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

$(document).ready(function () {
	UserInfoEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-userInfo-edit'), UserInfoEdit.updateUserInfo); //初始化表单校验规则
});