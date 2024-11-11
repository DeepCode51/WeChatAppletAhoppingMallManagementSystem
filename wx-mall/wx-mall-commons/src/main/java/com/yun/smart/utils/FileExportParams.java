package com.yun.smart.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class FileExportParams implements Serializable{
	
	private static final long serialVersionUID = -600144284024163528L;

	/**
	 * 导出数据
	 */
	private List<Map<String,Object>> datas;
	
	/**
	 * 导出文件类型,默认是".xlsx"
	 */
	private String fileType = ".xlsx";
	
	/**
	 * 导出文件名称
	 */
	private String fileName;
	
	/**
	 * 导出excel表头字段
	 */
	private String[] headers;
	
	/**
	 * 导出文件存储目录
	 */
	private String filePath;
	
	/**
	 * 导出excel表头字段对应的数据Key值
	 */
	private String[] keys;

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFullName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = (sdf.format(new Date()));
		if (StringUtils.isNotBlank(this.fileName)) {
			fileName = this.fileName.concat("-").concat(fileName);
		}
		return fileName.concat(this.fileType);
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
}
