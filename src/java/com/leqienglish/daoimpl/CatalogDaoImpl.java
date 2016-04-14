package com.leqienglish.daoimpl;

import java.util.List;

import com.leqienglish.dao.CatalogDao;
import com.leqienglish.entity.Catalog;
import com.leqienglish.entity.Content;
import com.leqienglish.entity.PageParam;

public class CatalogDaoImpl<T extends Catalog> extends TampletDaoImpl<T>  implements CatalogDao<T> {

    @Override
    public Class getEntityClass() {
        return Catalog.class;

    }
	
}
