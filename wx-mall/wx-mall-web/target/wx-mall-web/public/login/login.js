(function ($) {
    "use strict";
    
    // 登录
    var zdLoginIn = function () {
    	var params = $("#form-user-login").serializeObject();
    	AjaxUtils.sendAjaxRequest(params,'auth','login','POST').then(function(res){
			// 登录成功跳转到首页
    		SessionUtils.set('x-auth-token', res.data.authToken);
			window.location = "../index/index.html";
		},function(err){
	        layer.msg(err,{icon:5,time:1000});
		});
    } 

    $(document).ready(function () {
    	// 校验表单
    	CommUtils.cmInitForm($("#form-user-login"), zdLoginIn);
    });

}(jQuery));

