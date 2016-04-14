package com.leqienglish.biz;

import javax.servlet.http.HttpSession;


import com.leqienglish.entity.PageParam;
import com.leqienglish.entity.User;

public interface UserBiz {
	public String addUser(User user);
	public String login(String username, String password,HttpSession session);
	/**
	 * 找到以登录的用户
	 * @param session
	 * @return
	 */
	public String findUserFromSession(HttpSession session);

	public String getAllUser(PageParam pageParam);
	public String checkEmail(String email);
	
	/**
	 * 根据用户名或邮箱邮箱
	 * @param emailOrUserName
	 * @return
	 */
	public String findPassword(String emailOrUserName);
	/**
	 * 激活user
	 * @param uuid
	 * @return
	 */
	public String activityUser(String uuid);
	/**
	 * 通过uuid 获取用户
	 * @param uuid
	 * @return
	 */
	public String getUserByUuie(String uuid);
	/**
	 * 修改用户密码
	 * @param uuid
	 * @param password
	 * @return
	 */
	public String modifyPassword(String uuid , String password);
	/**
	 * 用户退出
	 * @return
	 */
	public String loginout(HttpSession session);
}
