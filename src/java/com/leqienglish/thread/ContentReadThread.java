package com.leqienglish.thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.leqienglish.entity.Content;

public class ContentReadThread extends Thread {
	private Content content;

	public ContentReadThread(Content content) {
		this.content = content;
	}

	public void run() {
		try {
			FileChannel fc = new FileInputStream(content.getContentPath()).getChannel();
			ByteBuffer byb = ByteBuffer.allocate(content.getContentLength().intValue());
			fc.read(byb, content.getStartIndex());
			fc.close();
			
			String content = new String(byb.array());
			int startindex = content.indexOf(":");
			this.content.setAllContent(content.substring(startindex));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getPath(){
		return "data.txt";
	}
}
