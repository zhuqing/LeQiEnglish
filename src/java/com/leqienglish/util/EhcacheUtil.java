/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.util;

import java.net.URL;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *
 * @author guona
 */
public class EhcacheUtil {

    private static CacheManager manager ;
    private static Cache contentCache;

    /**
     * 存储ContentId
     */
    public static String IDS = "ids";

    /**
     * 获取 content存储空间
     *
     * @return
     */
    private static Cache getCcontentCache() {
        if (contentCache == null) {
          //  URL url = EhcacheUtil.class.getResource("/ehcache.xml");
           // manager = CacheManager.create(url);
            manager = CacheManager.create();
            contentCache = manager.getCache("content");
        }

        return contentCache;
    }

    /**
     * 将数据放入ContentCache
     *
     * @param key
     * @param value
     */
    public static void putContentData(Object key, Object value) {
        Cache cache = getCcontentCache();
        if (cache == null) {
            return;
        }

        cache.put(new Element(key, value));
    }

    /**
     * 获取ContentData
     *
     * @param key
     * @return
     */
    public static Object getContentData(Object key) {
        Cache cache = getCcontentCache();
        if (cache == null) {
            return null;
        }

        Element element = cache.get(key);
        if (element == null) {
            return null;
        }

        return element.getValue();
    }
}
