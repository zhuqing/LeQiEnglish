package com.leqienglish.bizimpl;

import java.util.List;

import com.leqienglish.biz.CatalogBiz;
import com.leqienglish.dao.CatalogDao;
import com.leqienglish.entity.Catalog;
import com.leqienglish.entity.LeqiDTO;
import com.leqienglish.util.json.JSONUtil;

public class CatalogBizImpl implements CatalogBiz{

	private CatalogDao catalogDao;
	
	public CatalogDao getCatalogDao() {
		return catalogDao;
	}

	public void setCatalogDao(CatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}

	@Override
	public String save(Catalog catalog) {
		// TODO Auto-generated method stub
		String json = "";
		if(this.catalogDao.save(catalog)){
			json = JSONUtil.okMessage();
		}else{
			json = JSONUtil.errMessage();
		}
		return json;
	}

	@Override
	public String delete(Long id) {
		String json = "";
		if(this.catalogDao.delete(id)){
			json = JSONUtil.okMessage();
		}else{
			json = JSONUtil.errMessage();
		}
		return json;
	}

	@Override
	public String getAllCatalog() {
		// TODO Auto-generated method stub
		List<Catalog> list = this.catalogDao.queryAll();
                LeqiDTO leqi  = new LeqiDTO();
		String json = "";
		try {
			json = JSONUtil.toJsonObject(leqi).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json = JSONUtil.errMessage();
		}
		return json;
	}

}
