/**
 * 字典值常量
 * @author bonadeIT
 *
 */
var userTypeData = [];
var imageBizTypeData = [];
var commentTypeData = [];
var tipsBizTypeData = [];
var sellStatusData = [];
var goodsTypeData = [];
var orderStatusData = [];
var noticeTypeData = [];
var noticeStatusData = [];
var goodsStarData = [];

var DicInfoConsts = new Object({

	/** 类型：1=超级管理员 2=授权管理员 3=用户 */
	userType: "user_type",
	/** 类型：1=商品图 2=评论图 3=售后图 */
	imageBizType: "image_biz_type",
	/** 类型：1评论 2=售后 */
	commentType: "comment_type",
	/** 类型：1=小贴士 2=通知 */
	tipsBizType: "tips_biz_type",
	/** 状态：1=有货 2=无货 3=下架 4=活动 */
	sellStatus: "goods_sell_status",
	/** 类型：1=水果 2=零食 3=用品 */
	goodsType: "goods_type",
	/** 订单状态：1=待付款 2=待发货 3=已发货 4=已取消 5=已完成 6=售后中 */
	orderStatus: "order_status",
	/** 通知类型：1=已付款 2=申请售后 */
	noticeType: "notice_type",
	/** 状态：1=未读 2=已读 */
	noticeStatus: "notice_status",
	/** 评分等级：1=一颗星 2=两颗星 3=三颗星 4=四颗星 5=五颗星 */
	goodsStar: "goods_star",
	
	getUserType: function (){
		if (userTypeData.length == 0) {
			userTypeData = CommUtils.cmGetDictionaryData(DicInfoConsts.userType);
		}
		return userTypeData;
	},
	setUserType: function (elem, value){
		if (userTypeData.length == 0) {
			userTypeData = CommUtils.cmSetDictionaryData(DicInfoConsts.userType, elem, value);
		}
		return userTypeData;
	},
	getImageBizType: function (){
		if (imageBizTypeData.length == 0) {
			imageBizTypeData = CommUtils.cmGetDictionaryData(DicInfoConsts.imageBizType);
		}
		return imageBizTypeData;
	},
	setImageBizType: function (elem, value){
		if (imageBizTypeData.length == 0) {
			imageBizTypeData = CommUtils.cmSetDictionaryData(DicInfoConsts.imageBizType, elem, value);
		}
		return imageBizTypeData;
	},
	getCommentType: function (){
		if (commentTypeData.length == 0) {
			commentTypeData = CommUtils.cmGetDictionaryData(DicInfoConsts.commentType);
		}
		return commentTypeData;
	},
	setCommentType: function (elem, value){
		if (commentTypeData.length == 0) {
			commentTypeData = CommUtils.cmSetDictionaryData(DicInfoConsts.commentType, elem, value);
		}
		return commentTypeData;
	},
	getTipsBizType: function (){
		if (tipsBizTypeData.length == 0) {
			tipsBizTypeData = CommUtils.cmGetDictionaryData(DicInfoConsts.tipsBizType);
		}
		return tipsBizTypeData;
	},
	setTipsBizType: function (elem, value){
		if (tipsBizTypeData.length == 0) {
			tipsBizTypeData = CommUtils.cmSetDictionaryData(DicInfoConsts.tipsBizType, elem, value);
		}
		return tipsBizTypeData;
	},
	getSellStatus: function (){
		if (sellStatusData.length == 0) {
			sellStatusData = CommUtils.cmGetDictionaryData(DicInfoConsts.sellStatus);
		}
		return sellStatusData;
	},
	setSellStatus: function (elem, value){
		if (sellStatusData.length == 0) {
			sellStatusData = CommUtils.cmSetDictionaryData(DicInfoConsts.sellStatus, elem, value);
		}
		return sellStatusData;
	},
	getGoodsType: function (){
		if (goodsTypeData.length == 0) {
			goodsTypeData = CommUtils.cmGetDictionaryData(DicInfoConsts.goodsType);
		}
		return goodsTypeData;
	},
	setGoodsType: function (elem, value){
		if (goodsTypeData.length == 0) {
			goodsTypeData = CommUtils.cmSetDictionaryData(DicInfoConsts.goodsType, elem, value);
		}
		return goodsTypeData;
	},
	getOrderStatus: function (){
		if (orderStatusData.length == 0) {
			orderStatusData = CommUtils.cmGetDictionaryData(DicInfoConsts.orderStatus);
		}
		return orderStatusData;
	},
	setOrderStatus: function (elem, value){
		if (orderStatusData.length == 0) {
			orderStatusData = CommUtils.cmSetDictionaryData(DicInfoConsts.orderStatus, elem, value);
		}
		return orderStatusData;
	},
	getNoticeType: function (){
		if (noticeTypeData.length == 0) {
			noticeTypeData = CommUtils.cmGetDictionaryData(DicInfoConsts.noticeType);
		}
		return noticeTypeData;
	},
	setNoticeType: function (elem, value){
		if (noticeTypeData.length == 0) {
			noticeTypeData = CommUtils.cmSetDictionaryData(DicInfoConsts.noticeType, elem, value);
		}
		return noticeTypeData;
	},
	getNoticeStatus: function (){
		if (noticeStatusData.length == 0) {
			noticeStatusData = CommUtils.cmGetDictionaryData(DicInfoConsts.noticeStatus);
		}
		return noticeStatusData;
	},
	setNoticeStatus: function (elem, value){
		if (noticeStatusData.length == 0) {
			noticeStatusData = CommUtils.cmSetDictionaryData(DicInfoConsts.noticeStatus, elem, value);
		}
		return noticeStatusData;
	},
	getGoodsStar: function (){
		if (goodsStarData.length == 0) {
			goodsStarData = CommUtils.cmGetDictionaryData(DicInfoConsts.goodsStar);
		}
		return goodsStarData;
	},
	setGoodsStar: function (elem, value){
		if (goodsStarData.length == 0) {
			goodsStarData = CommUtils.cmSetDictionaryData(DicInfoConsts.goodsStar, elem, value);
		}
		return goodsStarData;
	},
});
