///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cn.duoduo.action.scheduel;
//
//import cn.duoduo.util.EhcacheUtil;
//import com.leqienglish.util.SessionBulder;
//import java.util.List;
//
///**
// *
// * @author guona
// */
//public class EhcacheScheduel {
//
//    /**
//     * 刷新缓存中的Ids
//     */
//    public void reflashIds() {
//        System.out.println("start reflashIds");
//        String hql = "select id from Content where contenttype=0";
//        List list = SessionBulder.query(hql);
//        EhcacheUtil.putContentData(EhcacheUtil.IDS, list);
//        System.out.println(list);
//    }
//}
