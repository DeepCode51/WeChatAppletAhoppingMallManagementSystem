package com.yun.smart.base;

/**
 * 基础参数
 * @author qihh
 *
 */
public class PageParams extends BaseParams {
	
	private static final long serialVersionUID = 3230174381744644550L;

	/** 每页显示条数  */
	private Integer pageSize;
	
	/** 当前页码  */
	private Integer page;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
