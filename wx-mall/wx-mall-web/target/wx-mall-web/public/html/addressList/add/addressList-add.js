var AddressListAdd = new Object();
	
// 保存表单
AddressListAdd.saveAddressList = function(form) {
	debugger;
	var params = $("#form-addressList-add").serializeObject();
	if ($('#defaultAddr').is(':checked')) {//这里就可以判断当前是否被选择了 
    	params['defaultAddr'] = '1';  
    } else { 
    	params['defaultAddr'] = '0';   
    } 
	AjaxUtils.sendAjaxRequest(params,'addressList','add','POST').then(function(res){
		layer.msg('已保存!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};
	
$(document).ready(function () {
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		increaseArea: '20%'
	});
	
	CommUtils.cmInitForm($("#form-addressList-add"), AddressListAdd.saveAddressList); //初始化表单校验规则
});