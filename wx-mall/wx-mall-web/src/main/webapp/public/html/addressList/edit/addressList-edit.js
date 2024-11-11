var AddressListEdit = new Object();
    
// 渲染表单数据
AddressListEdit.fillFormData = function () {
	var id = $.getUrlParam('id');
	AjaxUtils.sendAjaxRequest({"id": id},'addressList','searchDetail','POST').then(function(res){
		var addressListRow = res.data;
		$('#form-addressList-edit').find('input[name]').each(function () {
			$(this).val(addressListRow[$(this).attr('name')]);
		});
		$('textarea[name="linkAddr"]').val(addressListRow.linkAddr);
		if (addressListRow.defaultAddr == '1') {
			$('#defaultAddr').iCheck('check');
		}
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

// 更新表单
AddressListEdit.updateAddressList = function(form) {
	var params = $('#form-addressList-edit').serializeObject();
	if ($('#defaultAddr').is(':checked')) {//这里就可以判断当前是否被选择了 
    	params['defaultAddr'] = '1';  
    } else { 
    	params['defaultAddr'] = '0';   
    } 
	AjaxUtils.sendAjaxRequest(params,'addressList','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
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
	AddressListEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-addressList-edit'), AddressListEdit.updateAddressList); //初始化表单校验规则
});