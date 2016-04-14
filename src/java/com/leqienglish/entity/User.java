package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;


@JSONClass
public class User extends Entity{

	/**
	 * 性别默认为男
	 */
	@JSON(name="sex")
	private Integer sex=0;
	/**
	 * user email
	 */
	@JSON(name="email")
	private String email;
	/**
	 * 用户名称
	 */
	@JSON(name="username")
	private String username;
	/**
	 * 密码
	 */
	@JSON(name="password")
	private String password;

	/**
	 * 唯一编码
	 */
	@JSON(name="uuid")
	private String uuid;
	/**
	 * 用户状态 0 非激活状态 ， 1 激活状态
	 */
	@JSON(name="status")
	private Integer status;
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public class UserType{
		public final static int UN_ACTIVITY = 0;
		public final static int ACTIVITY = 1;
	}
}
