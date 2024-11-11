var AjaxUtils = new Object();

/**
 * 发送ajax请求-form表单
 * params 请求参数：{}
 * module 模块名： testDemo
 * path 请求路径：searchPage
 * method 请求方式：POST,GET
 */
AjaxUtils.sendAjaxRequest = function (params,module,path,method) {
	var url = ajaxUrl[module][path];
	console.info('ajax请求路径：'+url+",参数:"+JSON.stringify(params));
	// jQuery执行回调函数的实现
	var dtd = $.Deferred();// 新建一个deferred对象
	$.ajax({
		method: method,
		url: url,
		async : true,
		traditional : true,
		data: params,
		dataType:"json",
		success: function (result) {
			if (result && result.code == '0000') {
				dtd.resolve(result);
			} else if (result && result.code == '90001') {
				SessionUtils.clear();
	        	window.top.location.href = htmlPath + "login/index.html";
			} else {
				dtd.reject(result.msg);
			}
		},
		error: function (err) {
			dtd.reject("访问出错");
		},
		headers: {
            "x-auth-token":SessionUtils.get('x-auth-token')
        }
	});
	return dtd;
};

/**
 * 发送ajax请求-json格式
 * params 请求参数：{}
 * module 模块名： testDemo
 * path 请求路径：searchPage
 * method 请求方式：POST,GET
 */
AjaxUtils.sendAjaxJsonRequest = function (params,module,path,method) {
	var url = ajaxUrl[module][path];
	console.info('ajax请求路径：'+url+",参数:"+JSON.stringify(params));
	// jQuery执行回调函数的实现
	var dtd = $.Deferred();
	$.ajax({
		method: method,
		url: url,
		async : true,
		traditional : true,
		data : JSON.stringify(params),  
        contentType : "application/json",  
		dataType:"json",
		success: function (result) {
			if (result && result.code == '0000') {
				dtd.resolve(result);
			} else if (result && result.code == '90001') {
				SessionUtils.clear();
	        	window.top.location.href = htmlPath + "login/index.html";
			} else {
				dtd.reject(result.msg);
			}
		},
		error: function (err) {
			dtd.reject("访问出错");
		},
		headers: {
            "x-auth-token":SessionUtils.get('x-auth-token')
        }
	});
	return dtd;
};

/**
 * 同步请求-获取字典值
 */
AjaxUtils.sendSyncAjaxRequest = function (params,module,path,method) {
	var url = ajaxUrl[module][path];
	console.info('ajax请求路径：'+url+",参数:"+JSON.stringify(params));
	// jQuery执行回调函数的实现
	var dtd = $.Deferred();// 新建一个deferred对象
	$.ajax({
		method: method,
		url: url,
		async : false,
		data: params,
		dataType:"json",
		success: function (result) {
			if (result && result.code == '0000') {
				dtd.resolve(result);
			}else if (result && result.code == '90001') {
				SessionUtils.clear();
	        	window.top.location.href = htmlPath + "login/index.html";
			} else {
				dtd.reject(result.msg);
			}
		},
		error: function (err) {
			dtd.reject("访问出错");
		},
		headers: {
            "x-auth-token":SessionUtils.get('x-auth-token')
        }
	});
	return dtd;
};


/**
 * 查询本地json数据
 */
AjaxUtils.getJsonData = function(jsonUrl) {
	console.info('json请求路径：'+jsonUrl);
	var jsonData = new Array();
    $.ajax({
        url: jsonUrl,
        method: 'get',
        async: false,
        success: function (res) {
        	jsonData = res;
        }

    });
    return jsonData;
};

/**
 * 添加遮罩，避免重复提交
 */
$(document).ajaxStart(function(){
	layer.load();
}).ajaxStop(function(){
	layer.close(layer.load());
});