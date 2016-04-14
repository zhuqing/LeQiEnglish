package com.leqienglish.action;

import com.leqienglish.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class UpLoadAction {

    @RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "type") String type, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        FileUtil.getPath()
    }

}
