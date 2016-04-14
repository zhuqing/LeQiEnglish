package com.leqienglish.action;

//package cn.duoduo.action;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//
//import cn.duoduo.util.Util;
//
//public class VedioAction {
//	private String username;
//	private String identity;
//	private String json;
//	private String friends;
//	
//
//	public String getFriends() {
//		return friends;
//	}
//	public void setFriends(String friends) {
//		this.friends = friends;
//	}
//	public String getJson() {
//		return json;
//	}
//	public void setJson(String json) {
//		this.json = json;
//	}
//
//
//
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getIdentity() {
//		return identity;
//	}
//	public void setIdentity(String identity) {
//		this.identity = identity;
//	}
//	
//	
//	public String connect(){
//		if(this.friends!=null){
//			this.findFriend();
//		}else{
//			this.regist();
//		}
//		return "ok";
//	}
//	
//	private void findFriend(){
//		if(Util.userMap.containsKey(this.friends)){
//			String identity = Util.userMap.get(this.friends);
//			this.json = "<root type='lookup' username='"+this.friends+"' identity='"+identity+"'></root>";
//		}else{
//			this.json = "<root></root>";
//		}
//		
//	}
//	
//	private void regist(){
//		Iterator<String> keys = Util.userMap.keySet().iterator();
//		String root = "<root type='regist'>";
//		while(keys.hasNext()){
//			String key = keys.next();
//			root+="<item username='"+key+"' identify='"+ Util.userMap.get(key)+"'/>";
//		}
//		root +="</root>";
//		this.json = root;
//		Util.userMap.put(this.username, this.identity);
//	}
//}
