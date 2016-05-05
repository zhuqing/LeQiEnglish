package com.leqienglish.action;

import com.leqienglish.biz.ContentBiz;
import com.leqienglish.biz.DictBiz;
import com.leqienglish.entity.Content;
import com.leqienglish.entity.ContentTypeEnum;
import com.leqienglish.entity.PageParam;
import com.leqienglish.util.Util;
import com.leqienglish.util.enumutil.EnumUtil;
//import com.leqienglish.util.log.LOGGER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentAction extends ParentAction {

    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String SUMMARY = "summary";
    private static final String AUTHER = "author";
    private static final String FROM_WHERE = "fromWhere";
    private static final String TYPE = "type";
    private static final String CONTENT = "content";
    private static final String ICON_PATH = "iconPath";
    private static final String AUDIO_PATH = "audioPath";

    // private LOGGER logger = Log
    private static final Log LOG = LogFactory.getLog(ContentAction.class);

    private ContentBiz contentBiz;
    private DictBiz dictBiz;

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public @ResponseBody
    String insertContect(HttpServletRequest req, HttpServletResponse resp) {
        Content content = new Content();

        content.setTitle(this.getParmeter(ContentAction.TITLE, req));
        content.setContent(this.getParmeter(ContentAction.CONTENT, req));
        content.setIconPath(this.getParmeter(ContentAction.ICON_PATH, req));
        content.setAudioPath(this.getParmeter(AUDIO_PATH, req));
        content.setFromwhere(this.getParmeter(FROM_WHERE, req));

        content.setSummary(this.getParmeter(SUMMARY, req));

        content.setUserId(this.getLoginedUserId(req));
        String type = this.getParmeter(TYPE, req);
        if (type == null) {
            type = ContentTypeEnum.ARTICLE.name();
        }
        content.setContentType(ContentTypeEnum.valueOf(type));

        return this.getContentBiz().saveContent(content);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")

    public @ResponseBody
    String detail(@PathVariable("id") Long id) {
        return this.getContentBiz().getContent(id);
    }

    /**
     * 标题是否已经存在
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/hasTitle/{title}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String hasTitle(@PathVariable("title") String title) {
        return this.getContentBiz().hasTitle(title);
    }

    /**
     * 根据类型获取数据
     * @param type
     * @param page
     * @param size
     * @param req
     * @return 
     */
    @RequestMapping(value = "/getContentList/{type}/{page}/{size}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getContentList(@PathVariable("type") String type, @PathVariable("page") int page, @PathVariable("size") int size, HttpServletRequest req) {
        PageParam pageParam = this.createPage(page, size);
        if (!EnumUtil.hasEnum(ContentTypeEnum.class, type)) {
            type = ContentTypeEnum.ARTICLE.name();
        }
        return this.contentBiz
                .getAllContentByType(pageParam, ContentTypeEnum.valueOf(type), null);
    }
    
    @RequestMapping(value = "/updateReader/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String updateReader(@PathVariable("id") Long id){
        return this.contentBiz.updateReader(id);
    }

//
//    public String addWenZhang() {
//        Long id = this.insert(this.getType());
//        if (id == null) {
//            this.json = JSONUtil.errMessage();
//        } else {
//            if (getWords() != null && !getWords().isEmpty()) {
//                this.getDictBiz().saveWordsContent(id, getWords());
//            }
//
//            this.json = JSONUtil.okMessage();
//        }
//
//        return "ok";
//    }
//
//    public String insertFamousWord() {
//        this.setTitle("famous");
//        if (this.insert(LeQiType.TitleTip) == null) {
//            this.json = JSONUtil.errMessage();
//        } else {
//            this.json = JSONUtil.okMessage();
//        }
//
//        return "ok";
//        //this.json = this.insert(LeQiType.TitleTip);
//    }
//
//    public String deleteContent() {
//        if (this.getUserId().equals(this.getLoginedUserId())) {
//            this.json = this.contentBiz.deleteContent(this.getId());
//        } else {
//            this.json = JSONUtil.errMessage();
//        }
//
//        return "ok";
//    }
//
//    /**
//     * 是否已经存在了这个title的content
//     *
//     * @return
//     */
//    public String hasTitle() {
//        this.json = this.contentBiz.hasTitle(this.getTitle());
//        return "ok";
//    }
//
//    public String allPrilegeDeleteContent() {
//        if (this.getUsername().equals(LeQiType.USERNAME_DELTETE)) {
//            this.json = this.contentBiz.deleteContent(this.getId());
//        } else {
//            this.json = JSONUtil.errMessage();
//        }
//
//        return "ok";
//    }
//
//    public String getContentListByCatalogId() {
//        return "ok";
//    }
//
//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/getContentListByType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getContentListByType() {
//        PageParam page = this.createPage();
//
//        return this.contentBiz.getAllContentByType(page, this.getType(),
//                this.getLoginedUserId());
//    }
//
//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/getContentListByTypeAndCatalogs", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getContentListByTypeAndCatalogs() {
//        PageParam page = this.createPage();
//
//        return this.contentBiz.getAllContentByType(page, this.getType(),
//                this.getLoginedUserId());
//
//    }
//
//    @RequestMapping(value = "/getContentListByParentId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getContentListByParentId() {
//        PageParam page = this.createPage();
//        return this.contentBiz.getAllContentByParentId(page, this.getParentId(),
//                this.getLoginedUserId());
//
//    }
//
//    @RequestMapping(value = "/getMoreContent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getMoreContent() {
//        return this.contentBiz.getMoreContent(this.createPage(), this.getId(), this.getType());
//
//    }
//
//    @RequestMapping(value = "/getNewContent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getNewContent() {
//        return this.contentBiz.getNewContent(this.getId(), this.getType());
//
//    }
//
//    @RequestMapping(value = "/getContentInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getContentInfo(@PathVariable("type") int type, @PathVariable("id") long id) {
//        return this.contentBiz.getContent(id, type);
//
//    }
//
//    public String addComment() {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Comment comment = new Comment();
//        comment.setContent(this.getContent());
//
//        comment.setContentId(this.getId());
//
//        comment.setEmail(this.getEmail());
//
//        comment.setReComentId(this.getReCommentId());
//
//        comment.setCreateDate(formatter.format(new Date()));
//        comment.setUsername(this.getUsername());
//
//        //this.json = this.contentBiz.saveComment(comment);
//        return "ok";
//    }
    public String getCommentById() {
        //  this.json = this.contentBiz.getCommentById(this.getId());
        return "ok";
    }

    public String getComment() {
//        PageParam page = this.createPage();
//        this.json = this.contentBiz.getComment(page, this.getId());
        return "ok";
    }

    public String findAllFamousWords() {
        // this.json = this.contentBiz.getAllContentByType(this.createPage(), LeQiType.TitleTip, this.getLoginedUserId());
        return "ok";
    }

    /**
     * 上传跟读英语的课程
     *
     * @return
     */
//    public String uploadFellowEnglishZip() {
//        MultiPartRequestWrapper request = (MultiPartRequestWrapper) ServletActionContext
//                .getRequest();
//
//        Content content = new Content();
//        content.setId(Util.getID());
//        content.setCreateTime(System.currentTimeMillis() + "");
//        content.setTitle(this.getTitle());
//        content.setUserId(this.getUserId());
//        if (this.getParentId() == null) {
//            content.setContenttype(LeQiType.FELLOW_ENGLISH_BOOK);
//        } else {
//            content.setContenttype(LeQiType.FELLOW_ENGLISH_COURSE);
//            content.setParentId(parentId);
//        }
//
//        content.setContent(this.getContent());
//
//        // learn.setContent(Util.decryptByDES(this.content));
//        String fileName = this.getFileName(request);
//
//        InputStream is = this.getFileStream(request);
//
//        this.json = this.contentBiz.uploadFellowEnglish(content, is, fileName);
//        return this.returnMsg;
//    }
//    public String getContentNextUp() {
//        this.json = this.contentBiz.getContentNextAndUp(this.getId(),
//                this.getType());
//        return "ok";
//    }
    /**
     * 获取更多的Artic
     *
     * @return
     */
//    public String getMoreArtical() {
//        this.json = this.contentBiz.getMoreAritical(this.getType());
//        return "ok";
//    }
    /**
     * 上传图片
     *
     * @return
     */
//    public String updataImage() {
//        MultiPartRequestWrapper request = (MultiPartRequestWrapper) ServletActionContext
//                .getRequest();
//        String fileName = null;
//        InputStream is = null;
//        if (this.icon == null) {
//            fileName = this.getFileName(request);
//            is = this.getFileStream(request);
//        } else {
//            try {
//                is = new FileInputStream(this.icon);
//                fileName = this.fileName;
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        this.json = this.contentBiz.uploadFile(is, fileName);
//        return "ok";
//
//    }
    /**
     * 检测验证码
     *
     * @return
     */
//    public String checkYanZhengMa() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession();
//        System.out.println(session.getId() + "::" + this.getMessage());
//        String code = "";
//        if (session.getAttribute("piccode") == null) {
//            code = "---";
//        } else {
//            code = session.getAttribute("piccode").toString();
//        }
//
//        JSONObject jobject = new JSONObject();
//        jobject.put("piccode", code);
//        json = jobject.toString();
//        System.err.println(code);
//        return "ok";
//    }
//    public String getTitleTip() {
//        this.json = this.contentBiz.getTitleTip();
//        return "ok";
//    }
//
//    public String updateRecomment() {
//        this.json = this.contentBiz.updateRecomment(this.getId(),
//                this.getReCommentId());
//        return "ok";
//    }
//
//    public String updateReader() {
//        this.json = this.contentBiz.updateReader(this.getId(),
//                this.getReCommentId());
//        return "ok";
//    }
//    public ContentBiz getContentBiz() {
//        return contentBiz;
//    }
    public void setContentBiz(ContentBiz contentBiz) {
        this.contentBiz = contentBiz;
    }

    /**
     * @return the dictBiz
     */
    public DictBiz getDictBiz() {
        return dictBiz;
    }

    /**
     * @param dictBiz the dictBiz to set
     */
    public void setDictBiz(DictBiz dictBiz) {
        this.dictBiz = dictBiz;
    }

    /**
     * @return the contentBiz
     */
    public ContentBiz getContentBiz() {
        return contentBiz;
    }

}
