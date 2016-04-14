//package com.leqienglish.daoimpl;
//
//import java.util.List;
//
//import org.springframework.dao.DataAccessException;
//
//import com.leqienglish.dao.TitleTipDao;
//import com.leqienglish.entity.TitleTip;
//
//public class TitleTipDaoImpl extends TampleDaoImpl<TitleTip> implements TitleTipDao {
//
//	
//
//	@Override
//	public List<TitleTip> getTitleTips() {
//		// TODO Auto-generated method stub
//		String hql = "from TitleTip";
//		List<TitleTip> list = null;
//		try {
//			//list = this.getHibernateTemplate().find(hql);
//		} catch (DataAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	@Override
//	public boolean save(TitleTip titleTip) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
