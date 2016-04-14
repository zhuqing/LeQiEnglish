package com.leqienglish.dao;

import com.leqienglish.entity.Entity;
import java.util.List;

import com.leqienglish.entity.PageParam;

public interface TempDao<T extends Entity> {

    boolean save(T t);

    boolean delete(T t);

    boolean delete(Long id);

    List<T> queryAll(PageParam page);

    T query(Long id) ;
    
    List<T> queryAll();

}
