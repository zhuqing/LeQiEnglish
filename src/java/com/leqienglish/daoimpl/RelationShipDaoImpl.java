//package com.leqienglish.daoimpl;
//
//import com.leqienglish.dao.RelationShipDao;
//import com.leqienglish.entity.PageParam;
//import com.leqienglish.entity.RelationShip;
//import java.util.List;
//
//public class RelationShipDaoImpl<T extends RelationShip> extends TampleDaoImpl<T> implements RelationShipDao<T> {
//
//    @Override
//    public List<T> getAllListByHql() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public List<T> getListByHQLandPage(PageParam page) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public List<T> getCatalogAndContentListByCatalogId(
//            Long catalogId, PageParam page) {
//        // TODO Auto-generated method stub
//        String hql = "from CatalogAndContent where catalogId=" + catalogId;
//        return this.getListByHQLandPage(page, hql);
//    }
//
//    @Override
//    public List<T> getCatalogAndContentListByContentId(
//            Long contentId, PageParam page) {
//        // TODO Auto-generated method stub
//        String hql = "from CatalogAndContent where contentId=" + contentId;
//        return this.getListByHQLandPage(page, hql);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        return false;
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<T> getRelationShipBySourceIdAndType(Long sourceId, Integer type) {
//        String hql = "from RelationShip where sourceId=" + sourceId + " and relationShipType=" + type;
//        return this.getAllListByHql(hql);
//    }
//
//}
