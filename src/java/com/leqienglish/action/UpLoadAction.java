package com.leqienglish.action;

import com.leqienglish.util.FileUtil;
import com.leqienglish.util.json.JSONUtil;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class UpLoadAction extends ParentAction {

    private static final String IMAGE_TYPE = "image";
    private static final String TYPE = "type";

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String type = this.getParmeter(TYPE, request);
        if(type==null){
            type = "others";
        }
        
        String fileName = file.getOriginalFilename();

        try {
            String path = FileUtil.getPath(fileName, type);
            File dest = new File(path);
            file.transferTo(dest);
            JSONObject jsonObject = JSONUtil.okJSONObject();
            jsonObject = JSONUtil.createJSONObject(jsonObject, "path", FileUtil.getWebPath(path));
            jsonObject = JSONUtil.createJSONObject(jsonObject, "name", dest.getName());
            return jsonObject.toString();
        } catch (IOException ex) {
            return JSONUtil.errMessage();
        }

    }

}
