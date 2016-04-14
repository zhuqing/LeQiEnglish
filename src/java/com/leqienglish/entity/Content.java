package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;
import java.util.Date;




/**
 * 
 * @author guona
 * 
 */
@JSONClass
public class Content extends Entity {
	
	/**
	 * 标题
	 */
	@JSON(name="title")
	private String title;
	/**
	 * 类型
	 */
	@JSON(name="contenttype")
	private ContentTypeEnum contentType;
	/**
	 * 上传者的 用户Id
	 */
	@JSON(name="userId")
	private Long userId;
	/**
	 * 内容
	 */
	@JSON(name="content")
	private String content;
        
        /**
         * 摘要
         */
        @JSON(name="summary")
        private String summary;
	/**
	 * 内容所在文件的路径
	 */
	@JSON(name="contentPath")
	private String contentPath;
	/**
	 * 图标路径
	 */
	@JSON(name="iconPath")
	private String iconPath;
	/**
	 * 音频路径
	 */
	@JSON(name="audioPath")
	private String audioPath;

	
	/**
	 * 内容起始位置
	 */
	@JSON(name="startIndex")
	private long startIndex;
	/**
	 * 内容长度
	 */
	@JSON(name="contentLength")
	private Long contentLength;
	/**
	 * 阅读数
	 */
	@JSON(name="reader")
	private Integer reader;
	/**
	 * 评论数
	 */
	@JSON(name="recomment")
	private Integer recomment;
	/**
	 * 出处
	 */
	@JSON(name="fromwhere")
	private String fromwhere;

	/**
	 * 上一级的Id
	 */
	@JSON(name="parentId")
	private Long parentId;

	private User user;
	private String allContent;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getFromwhere() {
		return fromwhere;
	}

	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
	}

	public Integer getReader() {
		if (this.reader == null) {
			this.reader = 0;
		}
		return reader;
	}

	public void setReader(Integer reader) {
		this.reader = reader;
	}

	public Integer getRecomment() {
		if (this.recomment == null) {
			this.recomment = 0;
		}
		return recomment;
	}

	public void setRecomment(Integer recomment) {
		this.recomment = recomment;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;

	}

	private Date date;

	public Date getDate() {
		if (this.date != null) {
			this.date = new Date();
			date.setTime(Long.parseLong(this.getCreateTime()));
		}
		return date;
	}

	

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public String getAllContent() {
		return allContent;
	}

	public void setAllContent(String allContent) {
		this.allContent = allContent;
	}

	
	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the contentType
     */
    public ContentTypeEnum getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentTypeEnum contentType) {
        this.contentType = contentType;
    }



}
