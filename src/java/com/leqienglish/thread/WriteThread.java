package com.leqienglish.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.leqienglish.entity.Content;
import com.leqienglish.util.FileUtil;

/**
 * 将文章写的文件里
 * 
 * @author guona
 * 
 */
public class WriteThread extends Thread {
	private long contentId;
	private Content content;
	private String statement;
	private static SessionFactory sessionFactory;

	public WriteThread(Content content, String statement, long id) {
		this.content = content;
		this.contentId = id;
		this.statement = statement;
		if (sessionFactory == null) {
			// 步骤一：创建Configuration--读取Hibernate.properties内容到内存
			Configuration configuration = new Configuration();
			// 步骤二：加载域对象--读取Xxx.hbm.xml内容到内存
			configuration.configure();

			// 步骤三：创建SessionFactory实例
			sessionFactory = configuration.buildSessionFactory();
		}
	}

	public void run() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(statement);

		try {
			//File file = new File(FileUtil.getPath());
			//FileWriter fw = new FileWriter(file);   
			String path = FileUtil.getPath();
			
			FileChannel fc = new RandomAccessFile(path, "rw").getChannel();
			byte[] bs = sbf.toString().getBytes();
			Long length = (long) bs.length;
			
			content.setStartIndex(fc.size());
			content.setContentLength(length);
			content.setContentPath(path);
			fc.position(content.getStartIndex()); // 移到文件尾
			fc.write(ByteBuffer.wrap(bs));
			fc.close();

			Session session = sessionFactory.openSession();
                       // session.
			// 步骤五：开启事务
			Transaction transaction = session.beginTransaction();

			session.update(this.content);
			transaction.commit();
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
