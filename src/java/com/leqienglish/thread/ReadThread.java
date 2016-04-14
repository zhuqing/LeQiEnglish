package com.leqienglish.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.leqienglish.entity.Content;

public class ReadThread extends Thread{
	private Content content;
	private String filePath;
	private SessionFactory sessionFactory;
	  
	  
	public ReadThread(String filePath,Content  content){
		this.content = content;
		this.filePath = filePath;
		      // 步骤一：创建Configuration--读取Hibernate.properties内容到内存
		      Configuration configuration = new Configuration();
		      // 步骤二：加载域对象--读取Xxx.hbm.xml内容到内存
		      configuration.configure();
		     
		      // 步骤三：创建SessionFactory实例
		      sessionFactory= configuration.buildSessionFactory();
	}
	public void run(){
		if(this.filePath!=null){
			String content = this.readeFile(filePath);
			content = content.replace("\r\n", "<br/>&nbsp;&nbsp;");
			
			content = content.replaceAll("\\[[\\d|\\:|\\.]*\\]", "");
			if(content.length()>400){
				content = content.substring(0, 400)+".....";
			}
			
			this.content.setContent(content);
	
			Session session = sessionFactory.openSession();
		     
		      // 步骤五：开启事务
		      Transaction transaction= session.beginTransaction();;
			session.update(this.content);
			transaction.commit();
			  session.close();
		}
	}
	
	private String readeFile(String filePath){
		File file = new File(filePath);
		String content  = "";
		if(!file.exists()){
			return content;
		}
	
		try {
			
			InputStreamReader read = new InputStreamReader (new FileInputStream(file),"UTF-8");
			BufferedReader reader=new BufferedReader(read);
			String line;
			while(( line= reader.readLine()) !=null){
				content+=line+"\r\n";	
			}
			reader.close();
			read.close();
			
			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
}