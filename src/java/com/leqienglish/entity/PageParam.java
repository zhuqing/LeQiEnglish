package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;


@JSONClass
public class PageParam {
	/**
	 * 起始记录数
	 */
	@JSON(name="startIndex")
	private Integer startIndex;
	/**
	 * 总记录数
	 */
	@JSON(name="allCount")
	private Long allCount;
	/**
	 * 总页数
	 */
	@JSON(name="allPage")
	private Integer allPage;
	/**
	 * 每页显示数
	 */
	@JSON(name="pageSize")
	private Integer pageSize;
	/**
	 * 当前页数
	 */
	@JSON(name="currentPage")
	private Integer currentPage;
	
	public Integer getStartIndex() {
		this.startIndex = (this.currentPage-1)*this.pageSize;
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Long getAllCount() {
		return allCount;
	}
	public void setAllCount(Long allCount) {
		if(allCount%this.pageSize==0){
			this.allPage = (int) (allCount/this.pageSize);
		}else{
			this.allPage = (int) (allCount/this.pageSize+1);
		}
		this.allCount = allCount;
	}
	public Integer getAllPage() {
		return allPage;
	}
	
	public Integer getPageSize() {
		if(pageSize==null){
			return 10;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		if(currentPage==null){
			return 1;
		}
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		
		this.currentPage = currentPage;
	}
	
}
