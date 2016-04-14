package com.leqienglish.action;

//package cn.duoduo.action;
//
//import java.util.Date;
//
//import cn.duoduo.biz.CatalogBiz;
//import cn.duoduo.entity.Catalog;
//
//public class CatalogAction  extends ParentAction{
//	private CatalogBiz catalogBiz;
//	private String name;
//	private String desc; 
//	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDesc() {
//		return desc;
//	}
//
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
//
//	public CatalogBiz getCatalogBiz() {
//		return catalogBiz;
//	}
//
//	public void setCatalogBiz(CatalogBiz catalogBiz) {
//		this.catalogBiz = catalogBiz;
//	}
//
//	public String insert(){
//		Catalog catalog = new Catalog();
//		catalog.setDescription(this.desc);
//		catalog.setName(this.name);
//		catalog.setCreateDateTime(new Date());
//		this.json = this.catalogBiz.save(catalog);
//		return this.returnMsg;
//	}
//	
//	public String searchAll(){
//		this.json = this.catalogBiz.getAllCatalog();
//		return this.returnMsg;
//	}
//	
//	public String delete(){
//		this.json = this.catalogBiz.delete(this.getId());
//		return this.returnMsg;
//	}
//}
