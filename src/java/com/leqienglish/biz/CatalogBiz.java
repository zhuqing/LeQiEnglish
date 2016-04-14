package com.leqienglish.biz;

import com.leqienglish.entity.Catalog;

public interface CatalogBiz {
	/*
	 * 保存分类
	 */
	public String save(Catalog catalog);
	/**
	 * 删除Catalog
	 * @param id
	 * @return
	 */
	public String delete(Long id);
	/**
	 * 获取所有的catalog
	 * @return
	 */
	public String getAllCatalog() ;
}
