/**
 * ajax 请求url
 */
var ajaxUrl = new Object({
	
	/*
	 * 登录鉴权
	 */
	auth : {
		// pc登录
		login : "/smart/auth/pc/v1/login",
		// 获取用户登录信息
		getUserToken : "/smart/auth/pc/v1/getUserToken",
	},

	/*
	 * 访问日志
	 */
	accessLog : {
		// 查询分页
		searchPage : "/smart/accessLog/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/accessLog/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/accessLog/pc/v1/searchDetail",
		// 新增
		add : "/smart/accessLog/pc/v1/add",
		// 更新
		updateById : "/smart/accessLog/pc/v1/update",
		// 删除
		deleteById : "/smart/accessLog/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/accessLog/pc/v1/deleteByIds",
	},
	

    /*
	 * 地址
	 */
	addressList : {
		// 查询分页
		searchPage : "/smart/addressList/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/addressList/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/addressList/pc/v1/searchDetail",
		// 新增
		add : "/smart/addressList/pc/v1/add",
		// 更新
		updateById : "/smart/addressList/pc/v1/update",
		// 删除
		deleteById : "/smart/addressList/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/addressList/pc/v1/deleteByIds",
	},
	

    /*
	 * 图片
	 */
	fileImage : {
		// 查询分页
		searchPage : "/smart/fileImage/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/fileImage/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/fileImage/pc/v1/searchDetail",
		// 新增
		add : "/smart/fileImage/pc/v1/add",
		// 更新
		updateById : "/smart/fileImage/pc/v1/update",
		// 删除
		deleteById : "/smart/fileImage/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/fileImage/pc/v1/deleteByIds",
		// 上传图片
		uploadImage : "/smart/fileImage/pc/v1/uploadImage",
		// 设置主图标识
		changeMainLogo : "/smart/fileImage/pc/v1/changeMainLogo",
	},
	

    /*
	 * 文章
	 */
	fileTips : {
		// 查询分页
		searchPage : "/smart/fileTips/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/fileTips/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/fileTips/pc/v1/searchDetail",
		// 新增
		add : "/smart/fileTips/pc/v1/add",
		// 更新
		updateById : "/smart/fileTips/pc/v1/update",
		// 删除
		deleteById : "/smart/fileTips/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/fileTips/pc/v1/deleteByIds",
	},
	

    /*
	 * 产品信息
	 */
	goodsInfo : {
		// 查询分页
		searchPage : "/smart/goodsInfo/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/goodsInfo/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/goodsInfo/pc/v1/searchDetail",
		// 新增
		add : "/smart/goodsInfo/pc/v1/add",
		// 更新
		updateById : "/smart/goodsInfo/pc/v1/update",
		// 删除
		deleteById : "/smart/goodsInfo/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/goodsInfo/pc/v1/deleteByIds",
	},
	

    /*
	 * 评论
	 */
	orderComment : {
		// 查询分页
		searchPage : "/smart/orderComment/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/orderComment/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/orderComment/pc/v1/searchDetail",
		// 查询明细
		searchInfo : "/smart/orderComment/pc/v1/searchInfo",
		// 新增
		add : "/smart/orderComment/pc/v1/add",
		// 更新
		updateById : "/smart/orderComment/pc/v1/update",
		// 删除
		deleteById : "/smart/orderComment/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/orderComment/pc/v1/deleteByIds",
		// 批量回复
		batchResponse : "/smart/orderComment/pc/v1/batchResponse",
	},
	

    /*
	 * 订单
	 */
	orderInfo : {
		// 查询分页
		searchPage : "/smart/orderInfo/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/orderInfo/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/orderInfo/pc/v1/searchDetail",
		// 查询明细
		searchInfo : "/smart/orderInfo/pc/v1/searchInfo",
		// 新增
		add : "/smart/orderInfo/pc/v1/add",
		// 更新
		updateById : "/smart/orderInfo/pc/v1/update",
		// 删除
		deleteById : "/smart/orderInfo/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/orderInfo/pc/v1/deleteByIds",
	},
	

    /*
	 * 消息
	 */
	orderNotice : {
		// 查询分页
		searchPage : "/smart/orderNotice/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/orderNotice/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/orderNotice/pc/v1/searchDetail",
		// 新增
		add : "/smart/orderNotice/pc/v1/add",
		// 更新
		updateById : "/smart/orderNotice/pc/v1/update",
		// 删除
		deleteById : "/smart/orderNotice/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/orderNotice/pc/v1/deleteByIds",
		// 设置消息已读
		changeNoticeStatus : "/smart/orderNotice/pc/v1/changeNoticeStatus",
	},
	

    /*
	 * 用户
	 */
	userInfo : {
		// 查询分页
		searchPage : "/smart/userInfo/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/userInfo/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/userInfo/pc/v1/searchDetail",
		// 新增
		add : "/smart/userInfo/pc/v1/add",
		// 更新
		updateById : "/smart/userInfo/pc/v1/update",
		// 删除
		deleteById : "/smart/userInfo/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/userInfo/pc/v1/deleteByIds",
		// 查询用户基本信息-显示到导航栏
		getBaseInfo : "/smart/userInfo/pc/v1/getBaseInfo",
	},
	
	/*
	 * 字典表
	 */
	dicInfo : {
		// 查询分页
		searchPage : "/smart/dicInfo/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/dicInfo/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/dicInfo/pc/v1/searchDetail",
		// 新增
		add : "/smart/dicInfo/pc/v1/add",
		// 更新
		updateById : "/smart/dicInfo/pc/v1/update",
		// 删除
		deleteById : "/smart/dicInfo/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/dicInfo/pc/v1/deleteByIds",
		// 查询用户基本信息-显示到导航栏
		getBaseInfo : "/smart/dicInfo/pc/v1/getBaseInfo",
	},
	
	/*
	 * 快递
	 */
	expressInfo : {
		// 查询分页
		searchPage : "/smart/expressInfo/pc/v1/searchPage",
		// 查询列表
		searchList : "/smart/expressInfo/pc/v1/searchList",
		// 查询明细
		searchDetail : "/smart/expressInfo/pc/v1/searchDetail",
		// 新增
		add : "/smart/expressInfo/pc/v1/add",
		// 更新
		updateById : "/smart/expressInfo/pc/v1/update",
		// 删除
		deleteById : "/smart/expressInfo/pc/v1/delete",
		// 批量删除
		deleteByIds : "/smart/expressInfo/pc/v1/deleteByIds",
	},
	

});