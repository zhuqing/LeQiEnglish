package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;


@JSONClass
public class UserLearnE extends Entity{
	@JSON(name="userId")
	private Long userId;
	@JSON(name="learnEId")
	private Long learnEId;
	@JSON(name="type")
	private Integer type;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getLearnEId() {
		return learnEId;
	}
	public void setLearnEId(Long learnEId) {
		this.learnEId = learnEId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
