///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.leqienglish.daoimpl;
//
//import com.leqienglish.dao.DictDao;
//import com.leqienglish.entity.Content;
//import com.leqienglish.entity.Word;
//import com.leqienglish.util.LeQiType;
//import java.util.List;
//
///**
// *
// * @author guona
// */
//public class DictDaoImpl extends TampletDaoImpl<Content> implements DictDao {
//
//    @Override
//    public Content findWords(String word) {
//        String hql = "from Content where title='" + word + "' and contenttype=" + LeQiType.WORD;
//        List datas = this.getAllListByHql(hql);
//        if (datas == null || datas.isEmpty()) {
//            return null;
//        }
//
//        return (Content) datas.get(0);
//    }
//
//  
//
//    @Override
//    public void saveContent(Content content) {
//       this.save(content);
//    }
//
//}
