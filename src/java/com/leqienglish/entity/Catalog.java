package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;


@JSONClass
public class Catalog extends Entity{
	@JSON(name="parentId")
	private Long parentId;
	@JSON(name="name")
	private String name;
	@JSON(name="description")
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
