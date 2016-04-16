package com.leqienglish.action;

import com.leqienglish.util.FileUtil;
import com.leqienglish.util.json.JSONUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@Controller

public class UpLoadAction {

    private static final String IMAGE_TYPE="image";
    private static final String MP3_TYPE="mp3";
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
      
        String fileName = file.getOriginalFilename();
        
        try {
           String path =  FileUtil.getPath(fileName,IMAGE_TYPE);
           File dest = new File(path);
           file.transferTo(dest); 
           JSONObject jsonObject = JSONUtil.okJSONObject();
            return JSONUtil.createJSONObject(jsonObject, "path", FileUtil.getWebPath(path)).toString();
        } catch (IOException ex) {
           return JSONUtil.errMessage();
        }
        
       
        
    }

}
