//当前站点  http://localhost:8080
var baseHost = window.location.origin;

// html根目录 http://localhost:8080/OA/
var htmlPath = baseHost + '/public/';

/** *********************************引入meta文件******************************* */
var metaArray = [];
metaArray.push('<meta charset="utf-8">');
metaArray.push('<meta name="renderer" content="webkit|ie-comp|ie-stand">');
metaArray.push('<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">');
metaArray.push('<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />');
metaArray.push('<meta http-equiv="Cache-Control" content="no-siteapp" />');
for (var i = 0; i < metaArray.length; i++) {
	document.write(metaArray[i]);
}


/** *********************************引入icon文件******************************* */
var iconArray = [];
iconArray.push('<link rel="Bookmark" href="'+htmlPath+'static/base/favicon.ico" >');
iconArray.push('<link rel="Shortcut Icon" href="'+htmlPath+'static/base/favicon.ico" />');
for (var i = 0; i < iconArray.length; i++) {
	document.write(iconArray[i]);
}


/** *********************************引入css文件******************************* */
var cssArray = [];
cssArray.push('static/h-ui/css/H-ui.min.css');
cssArray.push('static/h-ui.admin/css/H-ui.admin.css');
cssArray.push('lib/Hui-iconfont/1.0.8/iconfont.css');
cssArray.push('static/h-ui.admin/skin/default/skin.css');
cssArray.push('static/h-ui.admin/css/style.css');
cssArray.push('lib/bootstrap-table/bootstrap-table.min.css');
cssArray.push('lib/select2/select2.min.css');

for (var i = 0; i < cssArray.length; i++) {
	var src = htmlPath + cssArray[i]
	document.write('<link rel="stylesheet" type="text/css" href="' + src + '" />');
}

/** *********************************引入js文件******************************* */
var jsArray = [];
jsArray.push('lib/jquery/1.9.1/jquery.min.js');
jsArray.push('lib/layer/2.4/layer.js');
jsArray.push('lib/laydate/laydate.js');
jsArray.push('static/h-ui/js/H-ui.min.js');
jsArray.push('static/h-ui.admin/js/H-ui.admin.js');
jsArray.push('lib/My97DatePicker/4.8/WdatePicker.js');
jsArray.push('lib/laypage/1.2/laypage.js');
jsArray.push('static/base/common.js');
jsArray.push('static/base/dicInfoConsts.js');
jsArray.push('static/base/sessionUtils.js');
jsArray.push('static/base/ajaxUrl.js');
jsArray.push('static/base/ajaxUtils.js');
jsArray.push('lib/bootstrap-table/bootstrap-table.min.js');
jsArray.push('lib/bootstrap-table/bootstrap-table-zh-CN.min.js');
jsArray.push('lib/jquery.validation/1.14.0/jquery.validate.js');
jsArray.push('lib/jquery.validation/1.14.0/validate-methods.js');
jsArray.push('lib/jquery.validation/1.14.0/messages_zh.js');
jsArray.push('lib/select2/select2.min.js');

for (var i = 0; i < jsArray.length; i++) {
	var src = htmlPath + jsArray[i]
	document.write('<script type="text/javascript" src="' + src + '"><\/script>');
}

