package com.leqienglish.daoimpl;

//package cn.duoduo.daoimpl;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.springframework.dao.DataAccessException;
//import org.springframework.orm.hibernate3.HibernateCallback;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//
//import cn.duoduo.dao.TempDao;
//import cn.duoduo.entity.Content;
//import cn.duoduo.entity.PageParam;
//
//public class TempDaoImpl<T>  {
//
//	@Override
//	public boolean save(T t) {
//		// TODO Auto-generated method stub
//		Transaction tran = this.getSession().beginTransaction();
//		try {
//			this.getSession().save(t);
//			tran.commit();
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			tran.rollback();
//		}
//		return false;
//	}
//
//	@Override
//	public boolean delete(T t) {
//		// TODO Auto-generated method stub
//		Transaction tran = this.getSession().beginTransaction();
//		try {
//			this.getSession().delete(t);
//			tran.commit();
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			tran.rollback();
//		}
//		return false;
//	}
//
//	@Override
//	public boolean update(T t) {
//		Transaction tran = this.getSession().beginTransaction();
//		try {
//			this.getSession().update(t);
//			tran.commit();
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			tran.rollback();
//		}
//		return false;
//	}
//
//	@Override
//	public List<T> getAllListByHql(String hql) {
//		List<T> list = null;
//		try {
//			list = this.getHibernateTemplate().find(hql);
//		} catch (DataAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	@Override
//	public List<T> getListByHQLandPage(final PageParam page, final String hql) {
//		String count = "select count(*) " + hql;
//		List<Long> countList = this.getHibernateTemplate().find(count);
//		if (countList.size() > 0) {
//			page.setAllCount(countList.get(0));
//		}
//		List<T> list = (List<T>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//
//					@Override
//					public List<Content> doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						// TODO Auto-generated method stub
//
//						Query query = session.createQuery(hql);
//						query.setFirstResult(page.getStartIndex());
//						query.setMaxResults(page.getPageSize());
//
//						return query.list();
//					}
//				});
//		if (list != null) {
//			return list;
//		}
//		return null;
//	}
//
//	@Override
//	public boolean delete(Long id, String className) {
//		Transaction tran = this.getSession().beginTransaction();
//		try {
//
//			Query query = this.getSessionFactory().openSession()
//					.createQuery("delete " + className + " where id = " + id);
//			int row = query.executeUpdate();
//			if (row > 0) {
//				tran.commit();
//				return true;
//			} else {
//				tran.rollback();
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			tran.rollback();
//			e.printStackTrace();
//
//		}
//		return false;
//	}
//
//	@Override
//	public boolean delete(Class<T> c) {
//		Transaction tran = this.getSession().beginTransaction();
//		try {
//            this.getHibernateTemplate().delete(c);
//			tran.commit();
//			return true;
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			tran.rollback();
//			e.printStackTrace();
//
//		}
//		return false;
//	}
//
//	@Override
//	public Long getAllCountByHQL(String hql) {
//		List list = this.getHibernateTemplate().find(hql);
//		if(list.size()>0){
//			return (Long) list.get(0);
//		}
//		
//		return 0L;
//	}
//
//}
