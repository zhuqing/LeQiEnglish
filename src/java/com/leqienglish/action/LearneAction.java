package com.leqienglish.action;

//package cn.duoduo.action;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
//
//import cn.duoduo.biz.LearnEBiz;
//import cn.duoduo.entity.Content;
//import cn.duoduo.entity.LearnE;
//import cn.duoduo.entity.PageParam;
//import cn.duoduo.util.LeQiType;
//import cn.duoduo.util.Util;
//import cn.duoduo.util.json.JSONUtil;
//
//import com.opensymphony.xwork2.ActionSupport;
//
//public class LearneAction extends ParentAction{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 5820763553371909282L;
//	private String createTime;
//	private String nameMD5;
//	private String path;
//	private LearnEBiz learnEBiz;
//	private Integer learneId;
//	
//	public Integer getLearneId() {
//		return learneId;
//	}
//
//	public void setLearneId(Integer learneId) {
//		this.learneId = learneId;
//	}
//
//
//	public String getPath() {
//		return path;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}
//
//	public LearnEBiz getLearnEBiz() {
//		return learnEBiz;
//	}
//
//	public void setLearnEBiz(LearnEBiz learnEBiz) {
//		this.learnEBiz = learnEBiz;
//	}
//
//	public String getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(String createTime) {
//		this.createTime = createTime;
//	}
//	public String getNameMD5() {
//		return nameMD5;
//	}
//
//	public void setNameMD5(String nameMD5) {
//		this.nameMD5 = nameMD5;
//	}
//
//	/**
//	 * 验证名称是否已经存在
//	 * @return
//	 */
//	public String verify(){
//		this.json = this.learnEBiz.verify(nameMD5);
//		return "ok";
//	}
//	
//	public String getAllLession(){
//		
//		this.json = learnEBiz.getLearnE(this.createPage());
//		return "ok";
//	}
//	
//	public String loadFile(){
//		this.json = this.learnEBiz.loadFile(this.getId().longValue(), this.getUserId().longValue());
//		return "ok";
//	}
//	
//	public String insertLession(){
//		Content learn =createLearen(LeQiType.LEARN_TYPE);
//		this.json = this.learnEBiz.insertLearnE(learn);
//		return this.returnMsg;
//	}
//	
//	public String createLearnBook(){
//		Content book = createLearen(LeQiType.LEARN_BOOK);
//		this.json = this.learnEBiz.insertLearnE(book);
//		return this.returnMsg;
//	}
//	
//	private Content createLearen(int type){
//		Content learn = new Content();
//		learn.setCreateTime(createTime);
//	
//		
//		learn.setTitle(this.getTitle());
//		
//		learn.setContent(Util.decryptByDES(this.getContent()));
//		return learn;
//	}
//	
//	public String addUserLearnE(){
//		this.json = this.learnEBiz.insertUserLearnE(this.getUserId().longValue(), learneId.longValue());
//		return "ok";
//	}
//	
//	
//	public String updataZip(){
//		if(this.getLoginedUserId()==-1){
//			this.json = JSONUtil.errMessage();
//			return this.returnMsg;
//		}
//		//this.
//		MultiPartRequestWrapper request=(MultiPartRequestWrapper) ServletActionContext.getRequest();
//		//request.
//		try {
//			//InputStream is = request.getInputStream();
//			
//			Content content = new Content();
//			content.setCreateTime(new Date().getTime()+"");
//			
//		
//			content.setTitle(this.getTitle());
//			
//			content.setUserId(this.getUserId().intValue());
//			//learn.setContent(Util.decryptByDES(this.content));
//			String header = request.getHeader("Content-Disposition");
//			String length = request.getHeader("Content-Length");
//			String filename[] = request.getFileNames("Filedata");
//			File[] fileArr = request.getFiles("Filedata");  
//	
//			System.out.println(header);
//			String realpath = request.getSession().getServletContext().getRealPath(request.getRequestURI());
//			  String parentPath = realpath.split(Util.projectName)[0];
//			  InputStream is =  new FileInputStream(fileArr[0]);
//	       if(header==null){
//	    	 
//			    parentPath+=Util.projectName;
//			  
//			    this.json = this.learnEBiz.uploadFile( learn,is, filename[0],parentPath);
//			    return this.returnMsg;
//			}
//			String[] headers = header.split(";");
//			
//			if(headers.length==3){
//				String file = headers[2].split("=")[1];
//				file = file.substring(1, file.length()-1);
//			    parentPath+=Util.projectName;
//				//this.json = this.learnEBiz.uploadImage(learn , is, file,parentPath);
//			}
//			
//			System.out.println(realpath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return this.returnMsg;
//	}
//	
//}
