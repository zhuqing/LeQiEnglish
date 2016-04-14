package com.leqienglish.dao;

import com.leqienglish.entity.CatalogAndContent;
import com.leqienglish.entity.PageParam;
import com.leqienglish.entity.RelationShip;
import java.util.List;

public interface RelationShipDao<T extends RelationShip> extends TempDao<T> {
	public List<T> getCatalogAndContentListByCatalogId(Long catalogId,PageParam page);
	public List<T> getCatalogAndContentListByContentId(Long contentId,PageParam page);
       public List<T> getRelationShipBySourceIdAndType(Long contentId , Integer type);
	
}
