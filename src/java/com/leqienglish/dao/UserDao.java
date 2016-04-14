package com.leqienglish.dao;

import java.util.List;

import com.leqienglish.entity.PageParam;
import com.leqienglish.entity.User;

public interface UserDao {
	public boolean addUser(User user);
	public List<User> getAllUser(PageParam pageParam);
	public User loginByEmail(String email);
	public User loginByUsername(String username);
	public User checkEmail(String email);
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	/**
	 * 根据uuid 获取用户
	 * @param uuid
	 * @return
	 */
	public User getUserByUuid(String uuid);
}
