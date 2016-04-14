/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.util;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author guona
 */
public class SessionBulder {

    private static SessionBulder instance = new SessionBulder();

    private static SessionFactory sessionFactory;

    private SessionBulder() {

    }

    /**
     * 初始化 Session
     * @return 
     */
    private static Session getSession() {
        if (sessionFactory == null) {
            // 步骤一：创建Configuration--读取Hibernate.properties内容到内存
            Configuration configuration = new Configuration();
            // 步骤二：加载域对象--读取Xxx.hbm.xml内容到内存
            configuration.configure();

            // 步骤三：创建SessionFactory实例
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory.openSession();
    }

    /**
     * 使用HSQL查询
     *
     * @param hquery
     * @return
     */
    public static List query(String hquery) {
        Session session = getSession();
        if (session == null) {
            return null;
        }
        Query query = session.createQuery(hquery);
        session.close();
        return query.list();
    }
}
