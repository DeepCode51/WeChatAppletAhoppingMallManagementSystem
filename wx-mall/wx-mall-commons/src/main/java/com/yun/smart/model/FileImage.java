package com.yun.smart.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yun.smart.base.BaseModel;
import com.yun.smart.utils.JsonUtils;

/**
 * Entity - 图片
 * 
 * @author qihh
 * @version 0.0.1
 */
@TableName("wx_file_image")
public class FileImage extends BaseModel {
	
	private static final long serialVersionUID = 2223208771866689280L;
	
	/** 主图标识：0=非主图 */
	public static final Integer NOT_MAIN_LOGO = 0;
	/** 主图标识：1=主图 */
	public static final Integer IS_MAIN_LOGO = 1;
	
	/** 顶部展示图：0=不是 */
	public static final Integer NOT_TOP_SHOW = 0;
	/** 顶部展示图：1=是 */
	public static final Integer IS_TOP_SHOW = 1;
	
	/** 类型：1=商品图 */
	public static final String BIZ_TYPE_1 = "1";
	/** 类型：2=评论图 */
	public static final String BIZ_TYPE_2 = "2";
	/** 类型：3=售后图 */
	public static final String BIZ_TYPE_3 = "3";
	
	/** 名称 */
	@TableField("file_name")
	private String fileName;
		
	/** 路径 */
	@TableField("file_path")
	private String filePath;
		
	/** 类型：1=商品图 2=评论图 3=售后图 */
	@TableField("biz_type")
	private String bizType;
		
	/** 业务ID */
	@TableField("biz_no")
	private String bizNo;
		
	/** 主图标识：0=非主图，1=主图 */
	@TableField("main_logo")
	private Integer mainLogo;
	
	/** 顶部展示图：0=不是，1=是 */
	@TableField("top_show")
	private Integer topShow;
		
			
	/** @return 名称 */
	public String getFileName() {
		return fileName;
	}

	/** @param fileName 名称 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
		
	/** @return 路径 */
	public String getFilePath() {
		return filePath;
	}

	/** @param filePath 路径 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
		
	/** @return 类型：1=商品图 2=评论图 3=售后图 */
	public String getBizType() {
		return bizType;
	}

	/** @param bizType 类型：1=商品图 2=评论图 3=售后图 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
		
	/** @return 业务ID */
	public String getBizNo() {
		return bizNo;
	}

	/** @param bizNo 业务ID */
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
		
	/** @return 主图标识：0=非主图，1=主图 */
	public Integer getMainLogo() {
		return mainLogo;
	}

	/** @param mainLogo 主图标识：0=非主图，1=主图 */
	public void setMainLogo(Integer mainLogo) {
		this.mainLogo = mainLogo;
	}
	
	/** @return 顶部展示图：0=不是，1=是 */
	public Integer getTopShow() {
		return topShow;
	}
	
	/** @param 顶部展示图：0=不是，1=是 */
	public void setTopShow(Integer topShow) {
		this.topShow = topShow;
	}	
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}