//package com.leqienglish.daoimpl;
//
//import java.util.List;
//
//
//import com.leqienglish.dao.UserDao;
//import com.leqienglish.entity.PageParam;
//import com.leqienglish.entity.User;
//
//public class UserDaoImpl extends TampleDaoImpl<User>  implements UserDao {
//
//	@Override
//	public boolean addUser(User user) {
//		// TODO Auto-generated method stub
//		
//		return this.save(user);
//	}
//
//
//
//	@Override
//	public List<User> getAllUser(final PageParam pageParam) {
//		// TODO Auto-generated method stub
//		final String hql = " from User";
//		return this.getListByHQLandPage(pageParam, hql);
//		
//		
//	}
//
//	@Override
//	public User loginByEmail(String email) {
//		String hql ="from User where email='"+email+"'";
//		return this.getUser(hql);
//	}
//
//	@Override
//	public User loginByUsername(String username) {
//		String hql ="from User where username='"+username+"'";
//		return this.getUser(hql);
//	}
//
//	@Override
//	public User checkEmail(String email) {
//		String hql = "from User where email='"+email+"'";
//		return this.getUser(hql);
//	}
//
//	@Override
//	public boolean updateUser(User user) {
//		// TODO Auto-generated method stub
//		
//		return this.update(user);
//	}
//
//	@Override
//	public User getUserByUuid(String uuid) {
//		// TODO Auto-generated method stub
//		String hql ="from User where uuid='"+uuid+"'";
//		return this.getUser(hql);
//	}
//	
//	/**
//	 * 通过hql 获取用户
//	 * @param hql
//	 * @return
//	 */
//	private User getUser(String hql){
//		List list =  this.getAllListByHql(hql);
//		if(list.size()>0){
//			return (User) list.get(0);
//		}
//		return null;
//	}
//
//}
