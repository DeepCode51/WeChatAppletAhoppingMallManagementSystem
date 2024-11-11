// 菜单实例化
var initMenus = function ($menus) {
	// 编译模板
	var menuTemplate = Template7.compile($('#template-menus').html());
    var menus = AjaxUtils.getJsonData('../json/menus.json');
	var data = {
			menus:menus || []
	};
	// 给模板加载数据
	var menuStr = menuTemplate(data);
	// 指定加载页面
	$menus.html(menuStr);
};

// 导航栏实例化
var initHeaders = function ($header) {
	// 编译模板
	var headerTemplate = Template7.compile($('#template-header').html());
	AjaxUtils.sendSyncAjaxRequest(null,'userInfo','getBaseInfo','POST').then(function(res){
		var data = {
				userInfo: res.data || {}
		};
		// 给模板加载数据
		var headerStr = headerTemplate(data);
		// 指定加载页面
		$header.html(headerStr);
		
	},function(err){
		layer.msg(err,{icon:5,time:1000});
	});
};

//绑定事件
var bindEvent = function () {
	$(".Hui-aside").on("click",".menu_dropdown dd li a",function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	/*左侧菜单*/
	$(".Hui-aside").Huifold({
		titCell:'.menu_dropdown dl dt',
		mainCell:'.menu_dropdown dl dd',
	});
	
	/*左侧菜单点击事件*/
	$(".Hui-aside").on("click",".menu_dropdown a",function(){
		Hui_admin_tab(this);
	});
	
	/*选项卡导航点击事件*/
	$("#Hui-msg").on("click","a",function(){
		Hui_admin_tab(this);
	});
	
	// 查看个人信息
	$("#myselfInfo").on('click',function(){
		layer.open({
			type: 1,
			area: ['300px','200px'],
			fix: false, //不固定
			maxmin: true,
			shade:0.4,
			title: '查看信息',
			content: '<div>管理员信息</div>'
		});
	});
	
	// 切换账户
	$("#changeRole").on('click',function() {
		console.info("切换账户");
	});
	
	// 退出登录
    $("#logOut").on('click',function() {
    	window.location.href = "../login/index.html";
    });
};

$(document).ready(function () {
	initMenus($('#sys-menu'));
	initHeaders($('#Hui-userbar'));
	$("body").Huitab({
		tabBar:".navbar-wrapper .navbar-levelone",
		tabCon:".Hui-aside .menu_dropdown",
		className:"current",
		index:0,
	});
	
	bindEvent();
	
});
