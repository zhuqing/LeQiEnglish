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
public abstract class ParentAction {
    
    /**
     * 获取参数值
     * @param key
     * @param request
     * @return 
     */
    protected String getParmeter(String key , HttpServletRequest request){
        Object value = request.getParameter(key);
        return value == null?null:value.toString();
    }

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    protected User getUser(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        if (session != null) {
            return (User) session.getAttribute(LeQiType.LOGIN_USER);
        } else {
            return null;
        }

    }

    protected Long getLoginedUserId(HttpServletRequest request) {
        User user = this.getUser(request);
        Long userId = user == null ? -1 : user.getId();
        return userId;
    }

    protected PageParam createPage(int currentPage, int pageSize) {
        PageParam page = new PageParam();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
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
}
