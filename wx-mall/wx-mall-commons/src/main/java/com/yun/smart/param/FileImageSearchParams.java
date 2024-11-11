package com.yun.smart.param;

import com.yun.smart.utils.JsonUtils;
import com.yun.smart.base.PageParams;
import org.springframework.beans.BeanUtils;
import com.yun.smart.model.FileImage;

/**
 * 查询图片请求参数类
 * 
 * @author qihh
 * @version 0.0.1
 * @date 2018-12-27 17:00:55
 */
public class FileImageSearchParams extends PageParams {

	private static final long serialVersionUID = -8973606101531495424L;

	/** 名称 */
	private String fileName;
	/** 路径 */
	private String filePath;
	/** 类型：1=商品图 2=评论图 3=售后图 */
	private String bizType;
	/** 业务编号或主键ID */
	private String bizNo;
	/** 主图标识：0=非主图，1=主图 */
	private Integer mainLogo;
	/** 顶部展示图：0=不是，1=是 */
	private Integer topShow;

	/** @return 名称 */
	public String getFileName() {
		return fileName;
	}
	/** @param 名称 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/** @return 路径 */
	public String getFilePath() {
		return filePath;
	}
	/** @param 路径 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/** @return 类型：1=商品图 2=评论图 3=售后图 */
	public String getBizType() {
		return bizType;
	}
	/** @param 类型：1=商品图 2=评论图 3=售后图 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	/** @return 业务编号或主键ID */
	public String getBizNo() {
		return bizNo;
	}
	/** @param 业务编号或主键ID */
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	/** @return 主图标识：0=非主图，1=主图 */
	public Integer getMainLogo() {
		return mainLogo;
	}
	/** @param 主图标识：0=非主图，1=主图 */
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
	
	/**
	 * 将请求参数转换成实体映射类，用作逻辑处理和查询参数类
	 * @return FileImage
	 */
	public FileImage toEntity() {
		FileImage fileImage = new FileImage();
		BeanUtils.copyProperties(this, fileImage);
		
		return fileImage;
	}
	
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
}