package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;
@JSONClass
public class CatalogAndContent extends Entity{
	/**
	 * 分类Id
	 */
	@JSON(name="catalogId")
	private Long catalogId;
	/**
	 * 内容Id
	 */
	@JSON(name="contentId")
	private Long contentId;
	
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	
}
