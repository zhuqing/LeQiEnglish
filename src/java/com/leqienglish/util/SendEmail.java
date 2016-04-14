package com.leqienglish.util;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail extends Thread {
	private String message;
	private Session session;
	private Folder folder;
	private Store store;
	private String pop = "smtp.163.com";
	private String username = "25zq@163.com";
	private String password = "sxzq2005";
	private String toEmail;
	private String title;
	public SendEmail(String title ,String message,String toEmail){
		this.message = message;
		this.toEmail = toEmail;
		this.title = title;
	}
	
	public void run(){
		POP3Auth auth = new POP3Auth();
		auth.setAccount(this.username, this.password);
		 Properties prop = System.getProperties();
		  Security.addProvider(new   com.sun.net.ssl.internal.ssl.Provider());
		    // final   String   SSL_FACTORY   =   "javax.net.ssl.SSLSocketFactory ";
		    // prop.setProperty( "mail.pop3.socketFactory.class ",   SSL_FACTORY);
		    // prop.setProperty( "mail.pop3.socketFactory.fallback ",   "false ");
		     prop.put("mail.smtp.host", this.pop);   
		     prop.put("mail.smtp.port","25");   
		     prop.put("mail.smtp.auth", "true");   
		     session = Session.getDefaultInstance(prop, auth);
		     try {
		    	 //store = session.getStore("pop3");
		    	  // store.connect(this.pop,this.username, password);
		    	   //System.out.println("11-------------------");
		    	  // folder = store.getDefaultFolder().getFolder("INBOX");
		    	 //  folder.open(Folder.READ_ONLY);
		    	 Message mailMessage = new MimeMessage(session); 
		    	 Address from = new InternetAddress(this.username); 
		    	 mailMessage.setFrom(from); 
		    	 Address to = new InternetAddress(this.toEmail);   
		         mailMessage.setRecipient(Message.RecipientType.TO,to);  

		         mailMessage.setSubject(this.title);

		         mailMessage.setSentDate(new Date());   

		         Multipart mainPart = new MimeMultipart();    
		         BodyPart html = new MimeBodyPart();    
		         html.setContent(this.message, "text/html; charset=utf-8");    
		         mainPart.addBodyPart(html);    

		         mailMessage.setContent(mainPart); 
		        
		         Transport.send(mailMessage);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	
	static class POP3Auth extends Authenticator {
		   String user, password;

		   void setAccount(String user, String password) {
		    this.user = user;
		    this.password = password;
		   }

		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);

		   }
		}
}
