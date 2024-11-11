var OrderCommentEdit = new Object();
    
// 渲染表单数据
OrderCommentEdit.fillFormData = function () {
	var id = $.getUrlParam('id');
	AjaxUtils.sendAjaxRequest({"id": id},'orderComment','searchInfo','POST').then(function(res){
		var orderCommentRow = res.data.orderComment;
		var orderImages = res.data.orderImages;
		
		$('input[name="id"]').val(orderCommentRow.id);
		$('textarea[name="content"]').val(orderCommentRow.content);
		$('textarea[name="responseContent"]').val(orderCommentRow.responseContent);
		
		// 评分
		var star = orderCommentRow.goodsStar;
		if (star) {
			var star_css = 'star-' + star;
			$('#starGroup').addClass(star_css);
			$('#goodsStar').html(star);
		}
		
		// 图片
		orderImages.forEach(function(item) {
			var imageHtml = $('<li><a href="'+url+'"><img src="'+item.filePath+'" style="width:120px; height:80px;"></a></li>');
			$('#comment-images').append(imageHtml);
		});
		
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};


// 更新表单
OrderCommentEdit.updateOrderComment = function(form) {
	var params = {
		id: $('#id').val(),
		responseContent: $('#responseContent').val()
	};
	
	AjaxUtils.sendAjaxRequest(params,'orderComment','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

$(document).ready(function () {
	OrderCommentEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-orderComment-edit'), OrderCommentEdit.updateOrderComment); //初始化表单校验规则
});