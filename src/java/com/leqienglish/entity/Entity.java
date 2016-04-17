package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;
import com.leqienglish.json.annotation.JSONDate;
import com.leqienglish.util.Util;
import java.io.Serializable;
import java.util.Date;

@JSONClass
public class Entity implements Serializable{
    public Entity(){
        this.setId(Util.getID());
        this.setCreateDateTime(new Date());
    }
	/**
	 * 实体id
	 */
	@JSON(name="id")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@JSON(name="createTime")
	private String createTime;
	@JSONDate(name="createDateTime")
	private Date createDateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
		if(this.createDateTime!=null){
			this.createTime = this.createDateTime.getTime()+"";
		}
	}
	
	
}
