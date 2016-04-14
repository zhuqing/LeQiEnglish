package com.leqienglish.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.leqienglish.entity.PageParam;
import com.leqienglish.entity.User;
import com.leqienglish.util.LeQiType;

/**
 * 所有Action的父类
 *
 * @author guona
 *
 */
public class ParentAction {

    private Long id;
    private Integer type;
    private Integer size;
    private Integer currentPage;
    private String email;
    private String username;
    private Integer reCommentId;
    private Long userId;
    private String content;
    private String title;
    protected final String returnMsg = "ok";

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    protected User getUser() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession(true);
//        if (session != null) {
//            return (User) session.getAttribute(LeQiType.LOGIN_USER);
//        } else {
//            return null;
//        }
        return null;
    }

    protected Long getLoginedUserId() {
        User user = this.getUser();
        Long userId = user == null ? -1 : user.getId();

        return userId;
    }

    protected PageParam createPage() {
        PageParam page = new PageParam();
        page.setCurrentPage(this.getCurrentPage());
        page.setPageSize(this.getSize());
        return page;
    }

//    protected String getFileName(MultiPartRequestWrapper request) {
//        String header = request.getHeader("Content-Disposition");
//        String type = request.getHeader("Content-Type");
//        if (header == null) {
//            String filename[] = request.getFileNames("Filedata");
//            return filename[0];
//        }
//        String[] headers = header.split(";");
//        if (headers.length == 3) {
//            String fileName = headers[2].split("=")[1];
//            fileName = fileName.substring(1, fileName.length() - 1);
//
//            return fileName;
//        }
//
//        return null;
//
//    }

//    protected InputStream getFileStream(MultiPartRequestWrapper request) {
//        InputStream is = null;
//        try {
//            File[] fileArr = request.getFiles("Filedata");
//
//            is = new FileInputStream(fileArr[0]);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//
//        }
//        return is;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        if (type == null) {
            type = 0;
        }
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getSize() {
        if (size == null) {
            return 10;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrentPage() {
        if (currentPage == null) {
            return 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getReCommentId() {
        return reCommentId;
    }

    public void setReCommentId(Integer reCommentId) {
        this.reCommentId = reCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
