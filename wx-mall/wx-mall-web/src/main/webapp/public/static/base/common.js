var CommUtils = new Object();
	
/**
 * 全屏打开新页面 
 * layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
 */
CommUtils.cmOpenIframe = function (title,url,successHandler){
	var index = layer.open({
		type: 2, //打开iframe
		maxmin: true, //显示最大化按钮
		title: title,
		content: url,
		success: successHandler //打开成功后的处理
	});
	layer.full(index);
};

/**
 * 在当前页面打开弹出框 
 * layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
 */
CommUtils.cmOpenModal = function (title,url,btn1Handler,btn2Handler,successHandler){
	var index = layer.open({
		type: 1, //打开iframe
		maxmin: true, //显示最大化按钮
		title: title,
		content: url,
		area: ['40%', '80%'], //宽和高
		btn: ['确认','取消'],
		yes: btn1Handler, //确认处理
		btn2: btn2Handler, //取消处理
		success: successHandler //打开成功后的处理
	});
	layer.full(index);
};

/**
 * 初始化table
 */
CommUtils.cmInitTable = function ($table,url,paramFunc) {
	var initTableParams = CommUtils.cmInitTableParams;
	if (paramFunc) {
		initTableParams = paramFunc;
	}
	$table.bootstrapTable('destroy');
	$table.bootstrapTable({
		method: 'POST',
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		url: url,//要请求数据的文件路径
		dataField: "data",//这是返回的json数组的key.默认好像是"rows".这里只有前后端约定好就行
		pagination: true,//是否分页
		sidePagination: 'server',//指定服务器端分页
		pageNumber: 1, //初始化加载第一页，默认第一页
		pageSize: 15,//单页记录数
		queryParams: initTableParams,//请求服务器时所传的参数
		responseHandler: CommUtils.cmResponseHandler,//请求数据成功后，渲染表格前的方法
		idField: 'id',
		search : false,
		showColumns: false,
	    ajaxOptions: {
	    	headers: {
	    		"x-auth-token":SessionUtils.get('x-auth-token')
	        }
	    }   
	});
};

/**
 * 分页结果渲染
 */
CommUtils.cmResponseHandler = function (result) {
	if (result && result.code == '0000') {
		return {
			total : result.data.total, //总页数,前面的key必须为"total"
			data : result.data.list //行数据，前面的key要与之前设置的dataField的值一致.
		};
	} else {
		layer.msg(result.msg,{icon: 5,time:1000});
	}
};

/**
 * 分页参数设置
 * 默认将搜索条件设置进去，#table_search_form
 */
CommUtils.cmInitTableParams = function (queryParams) {
	var params = {};
	var formData = $('#table_search_form').serializeArray();
	$.each(formData, function(i, field){
		if ($.trim(field.value) != '') {
			params[field.name] = field.value;
		}
	});
	
	params['page'] = queryParams.offset/queryParams.limit+1;
	params['pageSize'] = queryParams.limit;
	
	return params;
};

/**
 * 关闭当前iframe
 * refresh 是否需要刷新父页面
 */
CommUtils.cmCloseIframe = function (refresh) {
	if (refresh) {
		parent.location.reload();
	}
	//先得到当前iframe层的索引
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
};

/**
 * 初始化表单校验规则
 * submitHandler 提交表单实际处理方法
 */
CommUtils.cmInitForm = function ($form, submitHandler) {
	$form.validate({
		onkeyup: false,
		focusCleanup: true,
		success: "valid",
		submitHandler: submitHandler
	});
};

/**
 * 通用方法：根据ID删除一行数据
 */
CommUtils.cmDeleteRow = function (id, module, $table) {
	layer.confirm('确认要删除吗？',function(){
		AjaxUtils.sendAjaxRequest({"id": id},module,'deleteById','POST').then(function (res) {
			layer.msg('已删除!',{icon:1,time:1000},function(){
				$table.bootstrapTable('remove',{field:'id',values:[id]});
			});
		},function (err) {
			layer.msg(err,{icon:5,time:1000});
		});
	});
};

/**
 * 通用方法：根据勾选批量删除行
 */
CommUtils.cmDeleteRows = function (module, $table) {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length == 0) {
		layer.msg('请选择删除项！',{icon:5,time:1000});
		return;
	}
	var ids = [];
	rows.forEach(function(item){
		ids.push(item.id);
	});
	
	layer.confirm('确认要全部删除吗？',function(){
		AjaxUtils.sendAjaxRequest({"ids": ids}, module,'deleteByIds','POST').then(function (res) {
			layer.msg('已删除!',{icon:1,time:1000},function(){
				$table.bootstrapTable('remove',{field:'id',values:ids});
			});
		},function (err) {
			layer.msg(err,{icon:5,time:1000});
		});
	});
};

/**
 * 初始化日期控件
 * $input 控件元素
 * type ['year','month','date','time','datetime']
 */
CommUtils.cmInitDate = function (elem,type) {
	var elem = elem; if ($.trim(elem) == '') {layer.msg('日期控件未指定元素',{icon:5,time:1000});}
	var type = type; if ($.trim(type) == '') {type = 'date'}
	
	laydate.render({ 
		elem: elem, //指定控件
		type: type, //控件类型
		isInitValue: true //是否允许填充初始值
	});
};

/**
 * 获取字典值
 */
CommUtils.cmGetDictionaryData = function (dicName) {
	var result = new Array();
	var params = {"dicName": dicName};
	AjaxUtils.sendSyncAjaxRequest(params,'dicInfo','searchList','POST').then(function(res){
		res.data.forEach(function (item) {
			result.push({
				id: item.dicValue,
				text: item.dicText
			});
		});
		
	},function(err){
		layer.msg('获取'+dicName+'字典值错误:'+err,{icon:5,time:1000});
	});
	
	return result;
}

/**
 * 获取字典值并渲染赋值
 */
CommUtils.cmSetDictionaryData = function (dicName, elem, value) {
	var result = new Array();
	var params = {"dicName": dicName};
	AjaxUtils.sendSyncAjaxRequest(params,'dicInfo','searchList','POST').then(function(res){
		res.data.forEach(function (item) {
			result.push({
				id: item.dicValue,
				text: item.dicText
			});
			$(elem).select2({data: result,width:'270px'});
			if (value) {
				$(elem).val(value);
			}
		});
		
	},function(err){
		layer.msg('获取'+dicName+'字典值错误:'+err,{icon:5,time:1000});
	});
	
	return result;
}

/**
 * 根据字典值value获取字典值文本
 */
CommUtils.cmFormatDictionary = function (dicData, dicValue) {
	if (dicData == null || dicData.length == 0){
		return dicValue;
	}
	for (i=0; i < dicData.length; i++) {
		if (dicData[i].id == dicValue) {
			return dicData[i].text;
		}
	}
	return dicValue;
};


/**
 * 表单序列化
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

/**
 * 格式化日期 $.formatDate(data,'yyyy-MM-dd HH:mm:ss')
 * @param date 日期
 * @param format 格式
 * @returns
 */
$.formatDate=function (date,format){
	if (!date) return ;   
    if (!format) format = "yyyy-MM-dd"; 
    switch(typeof date) {   
        case "string":   
            date = new Date(date.replace(/-/g, "/"));   
            break;   
        case "number":   
            date = new Date(date);   
            break;   
    }
    if (!date instanceof Date) return;   
    
    var dict = {   
        "yyyy": date.getFullYear(),   
        "M": date.getMonth() + 1,   
        "d": date.getDate(),   
        "H": date.getHours(),   
        "m": date.getMinutes(),   
        "s": date.getSeconds(),   
        "MM": ("" + (date.getMonth() + 101)).substr(1),   
        "dd": ("" + (date.getDate() + 100)).substr(1),   
        "HH": ("" + (date.getHours() + 100)).substr(1),   
        "mm": ("" + (date.getMinutes() + 100)).substr(1),   
        "ss": ("" + (date.getSeconds() + 100)).substr(1)   
    };
 
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function() {   
        return dict[arguments[0]];   
    });
};


/**
 * 获得地址栏参数值
 */ 
$.getUrlParam = function (name) {
	// 用该属性获取页面 URL 地址从问号 (?) 开始的 URL（查询部分）
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
};
