var OrderInfoEdit = new Object();
    
// 渲染表单数据
OrderInfoEdit.fillFormData = function () {
	var orderNo = $.getUrlParam('orderNo');
	AjaxUtils.sendAjaxRequest({"orderNo": orderNo},'orderInfo','searchInfo','POST').then(function(res){
		var orderInfo = res.data.orderInfo;
		var orderExpress = res.data.orderExpress;
		var orderComment1 = res.data.orderComment1;
		var orderComment2 = res.data.orderComment2;
		var orderImages = res.data.orderImages;
		OrderInfoEdit.initForm(orderInfo);
		OrderInfoEdit.initExpress(orderExpress);
		OrderInfoEdit.initComment(orderComment1, orderComment2);
		OrderInfoEdit.initImages(orderImages);
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

/**
 * 渲染订单数据
 */
OrderInfoEdit.initForm = function (orderInfo) {
	$('input[name="id"]').val(orderInfo.id);
	$('input[name="orderStatus"]').each(function () {
		if ($(this).val() == orderInfo.orderStatus) {
			$(this).iCheck('check');
		}
	});
	$('#goodsImage').attr('src',orderInfo.filePath);
	$('#goodsName').html(orderInfo.goodsName);
	$('input[name="userName"]').val(orderInfo.userName);
	$('input[name="createTime"]').val(orderInfo.createTime);
	$('input[name="linkMan"]').val(orderInfo.linkMan);
	$('input[name="linkPhone"]').val(orderInfo.linkPhone);
	$('input[name="linkAddr"]').val(orderInfo.linkAddr);
}

/**
 * 渲染订单物流数据
 */
OrderInfoEdit.initExpress = function (orderExpress) {
	if (orderExpress) {
		$('#expressName').html('<span class="c-red">*</span>'+orderExpress.expressName+'：');
		$('input[name="expressNo"]').val(orderExpress.expressNo);
	}
}

/**
 * 渲染订单评论数据
 */
OrderInfoEdit.initComment = function (orderComment1, orderComment2) {
	if (orderComment1) {
		var star = orderComment1.goodsStar;
		if (star) {
			var star_css = 'star-' + star;
			$('#starGroup').addClass(star_css);
			$('#goodsStar').html(star);
		}
		$('textarea[name="content1"]').val(orderComment1.content);
	}
	
	if (orderComment2) {
		$('textarea[name="content2"]').val(orderComment2.content);
	}
}

/**
 * 渲染订单图片数据
 */
OrderInfoEdit.initImages = function (orderImages) {
	if (orderImages) {
		orderImages.forEach(function(item) {
			// 评论图
			if (item.bizType == '2') {
				var imageHtml = $('<li><a href="#"><img src="'+item.filePath+'" style="width:100px; height:65px;"></a></li>');
				$('#comment-images-1').append(imageHtml);
			}
			// 售后图
			if (item.bizType == '3') {
				var imageHtml = $('<li><a href="#"><img src="'+item.filePath+'" style="width:100px; height:65px;"></a></li>');
				$('#comment-images-2').append(imageHtml);
			}
		});
	}
}

// 更新表单
OrderInfoEdit.updateOrderInfo = function(form) {
	var params = $('#form-orderInfo-edit').serializeObject();
	params['orderStatus'] = $('input[name="orderStatus"]:checked').val();
	AjaxUtils.sendAjaxRequest(params,'orderInfo','updateById','POST').then(function(res){
		layer.msg('已更新!',{icon:1,time:1000},function(){
			CommUtils.cmCloseIframe(true);
		});
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

$(document).ready(function () {
	$('.skin-minimal input').iCheck({
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	OrderInfoEdit.fillFormData(); //渲染表单数据
	CommUtils.cmInitForm($('#form-orderInfo-edit'), OrderInfoEdit.updateOrderInfo); //初始化表单校验规则
});