package com.leqienglish.bizimpl;

import com.leqienglish.biz.ContentBiz;
import com.leqienglish.dao.CommentDao;
import com.leqienglish.dao.ContentDao;
import com.leqienglish.dao.RelationShipDao;
import com.leqienglish.dao.TitleTipDao;
import com.leqienglish.entity.CatalogAndContent;
import com.leqienglish.entity.Content;
import com.leqienglish.entity.ContentTypeEnum;
import com.leqienglish.entity.LeqiDTO;
import com.leqienglish.entity.PageParam;
import com.leqienglish.entity.TitleTip;
import com.leqienglish.json.handler.JSONObjectHandler;
import com.leqienglish.util.EhcacheUtil;

import com.leqienglish.util.FileUtil;
import com.leqienglish.util.Util;
import com.leqienglish.util.json.JSONUtil;

import com.leqienglish.util.operator.OperatorEnum;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sf.json.JSONObject;

public class ContentBizImpl implements ContentBiz {

    // private LOGGER logger = LOGGER.instance(ContentBizImpl.class);
    private ContentDao<Content> contentDao;
    private TitleTipDao titleTipDao;
    private CommentDao commentDao;
    private RelationShipDao relationShipDao;

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void setTitleTipDao(TitleTipDao titleTipDao) {
        this.titleTipDao = titleTipDao;
    }

    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    @Override
    public String saveContent(Content content) {
        Boolean isSave = this.contentDao.save(content);
        if (!isSave) {
            return JSONUtil.errMessage();
        }
        return JSONUtil.okMessage();
    }

    @Override
    public String getAllContent(PageParam page, Long userId) {
        // TODO Auto-generated method stub
        List<Content> list = this.contentDao.queryAll(page);
        return this.makeContent2Json(list, page);
    }

    /**
     * 创建文章的Json
     *
     * @param list
     * @param page
     * @param userId
     * @return
     */
    private String makeContent2Json(List<Content> list, PageParam page) {

        try {
            LeqiDTO leqiDTO = new LeqiDTO();
            leqiDTO.setPage(page);
            leqiDTO.setValue(list);
            JSONObjectHandler handler = new JSONObjectHandler();
            return handler.toJSON(leqiDTO).toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return JSONUtil.errMessage();

    }

    @Override
    public String getContent(Long id) {

        JSONObject json = new JSONObject();
        Content content = contentDao.query(id);
        if (content != null) {

            try {

                json = JSONUtil.toJsonObject(content);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return JSONUtil.errMessage();
            }

        } else {
            return JSONUtil.errMessage();
        }

        return json.toString();
    }

    /**
     * 创建Catalog与Content的关系
     *
     * @param catalogs
     * @param contentId
     * @return
     */
    private boolean insertDataToCatalogAndContent(String catalogs, long contentId) {

        if (catalogs == null || catalogs.isEmpty()) {
            return true;
        }
        if (catalogs.endsWith(";")) {
            catalogs = catalogs.substring(0, catalogs.length() - 1);
        }
        String[] catalogIdArr = catalogs.split(";");
        for (String catalogId : catalogIdArr) {
            CatalogAndContent cc = new CatalogAndContent();
            cc.setCatalogId(Long.valueOf(catalogId));
            cc.setContentId(contentId);
            cc.setCreateDateTime(new Date());
            if (!this.relationShipDao.save(cc)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getContentNextAndUp(Long id, ContentTypeEnum type) {
        JSONObject json = new JSONObject();
        // WenZhang perContent = this.wenZhangDao.getWenZhangByNextOrUp(id,
        // true);
        // WenZhang nextContent = this.wenZhangDao.getWenZhangByNextOrUp(id,
        // false);
        // JSONObject json = new JSONObject();
        // if(perContent!=null){
        // json.put("per", 1);
        // JSONObject per = new JSONObject();
        // per.put("id", perContent.getId());
        // per.put("title", perContent.getTitle());
        // // per.put("contentId", perContent.getContentId());
        // json.put("perobj", per);
        // }else{
        // json.put("per", 0);
        // }
        //
        // if(nextContent!=null){
        // json.put("next", 1);
        // JSONObject next = new JSONObject();
        // next.put("id", nextContent.getId());
        // next.put("title", nextContent.getTitle());
        // //next.put("contentId", nextContent.getContentId());
        // json.put("nextobj", next);
        // }else{
        // json.put("next", 0);
        // }

        return json.toString();
    }

    @Override
    public String getAllContentByType(PageParam page, ContentTypeEnum type, Long userId) {
        List<Content> listContent = null;

        listContent = this.contentDao.getAllContent(page, type);

        return this.makeContent2Json(listContent, page);

    }

    @Override
    public String getAllContentByParentId(PageParam page, Long contentId,
            Long userId) {
        List<Content> listContent = this.contentDao.getAllContenByParentId(
                page, contentId);
        return this.makeContent2Json(listContent, page);
    }

    private boolean scal(String source, String result) {
        try {
            System.out.println(result);
            File res = new File(result);
            res.createNewFile();
            BufferedImage src = ImageIO.read(new File(source));
            int width = src.getWidth(); // �õ�Դͼ��
            int height = src.getHeight(); // �õ�Դͼ��
            int towidth = 100;
            int toheight = (int) (height * (100 * 1.0 / width));
            // Image image = src.getScaledInstance(width, height,
            // Image.SCALE_DEFAULT);
            Image image = src.getScaledInstance(towidth, toheight,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(towidth, toheight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // ������С���ͼ
            g.dispose();
            ImageIO.write(tag, "JPEG", res);// ������ļ���
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTitleTip() {
        // TODO Auto-generated method stub
        JSONObject json = new JSONObject();
        try {
            if (Util.listTitleTip == null) {
                Util.listTitleTip = this.titleTipDao.getTitleTips();
            }

            if (Util.listTitleTip == null) {
                return JSONUtil.errMessage();
            }
            TitleTip titleTip = this
                    .getTitleTipRandom(Util.listTitleTip.size());
            json.put("title", titleTip.getTitleTip());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put("title", "error");
        }

        return json.toString();
    }

    private TitleTip getTitleTipRandom(int length) {
        int index = (int) (Math.random() * length);
        return Util.listTitleTip.get(index);
    }

//    @Override
//    public String saveComment(Comment comment) {
//        // TODO Auto-generated method stub
//        JSONObject json = new JSONObject();
//        if (this.commentDao.save(comment)) {
//            json.put("msg", "ok");
//        } else {
//            json.put("msg", "error");
//        }
//        return json.toString();
//    }
//    @Override
//    public String getComment(PageParam page, Long contentId) {
//        // TODO Auto-generated method stub
//
//        JSONObject json = new JSONObject();
//        List<Comment> list = this.commentDao.getCommentByContentId(contentId,
//                page);
//        json.put("currentPage", page.getCurrentPage());
//        json.put("pageSize", page.getPageSize());
//        json.put("allPage", page.getAllPage());
//        json.put("allCount", page.getAllCount());
//        JSONArray array = new JSONArray();
//        // int count = 10;
//        // while(count>0){
//        for (int i = 0; i < list.size(); i++) {
//            Comment comment = list.get(i);
//            JSONObject object = new JSONObject();
//            object.put("username", comment.getUsername());
//            object.put("email", comment.getEmail());
//            object.put("id", comment.getId());
//            object.put("createDate", comment.getCreateDate());
//            object.put("content", comment.getContent());
//            object.put("reCommentId", comment.getReComentId() == null ? -1
//                    : comment.getReComentId());
//            array.add(object);
//
//        }
//        // count--;
//        // }
//        json.put("list", array);
//        return json.toString();
//    }
//    @Override
//    public String getCommentById(long id) {
//        Comment comment = this.commentDao.getCommentById(id);
//        JSONObject json = new JSONObject();
//        if (comment != null) {
//            json.put("username", comment.getUsername());
//            // json.put("id", comment.getUsername());
//            json.put("content", comment.getContent());
//            json.put("reCommentId", comment.getReComentId() == null ? -1
//                    : comment.getReComentId());
//        } else {
//            json.put("msg", "error");
//        }
//        return json.toString();
//    }
    @Override
    public String updateRecomment(long id) {

        Content content = this.contentDao.query(id);
        content.setRecomment(content.getRecomment() + 1);
        if (this.contentDao.addRecoment(content)) {
            return JSONUtil.okMessage();
        } else {
            return JSONUtil.errMessage();
        }

    }

    private String update(Content content) {
        if (this.contentDao.updateContent(content)) {
            return JSONUtil.okMessage();
        } else {
            return JSONUtil.errMessage();
        }
    }

    @Override
    public String updateReader(long id) {

        Content content = this.contentDao.query(id);
        content.setReader(content.getReader() + 1);
        if (this.contentDao.addReader(content)) {

            return JSONUtil.okMessage();
        } else {
            return JSONUtil.errMessage();
        }
    }

    @Override
    public String deleteContent(long id) {
        // TODO Auto-generated method stub

        if (this.contentDao.delete(id)) {
            return JSONUtil.okMessage();
        } else {
            return JSONUtil.errMessage();
        }
    }

    @Override
    public String uploadFile(InputStream is, String fileName) {
        // TODO Auto-generated method stub
        JSONObject json = new JSONObject();
        // String filePath = FileUtil.getDirPath() + FileUtil.getRadomName(fileName);
//
//        try {
//            if (!FileUtil.saveFile(is, filePath)) {
//                return JSONUtil.errMessage();
//            }
//            json.put("msg", "ok");
//            int index = filePath.indexOf("file/");
//            json.put("path", filePath.substring(index, filePath.length()));
//        } catch (Exception e) {
//            json.put("err", "upload error!!!");
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return json.toString();
    }

//    /**
//     * 上传文件
//     */
//    @Override
//    public String uploadFellowEnglish(Content content, InputStream is,
//            String fileName) {
//
//        String filePath = FileUtil.getDirPath() + FileUtil.getRadomName(fileName);
//        this.logger.info(filePath);
//        long lenth = 0L;
//        try {
//            lenth = is.available();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return JSONUtil.errMessage();
//        }
//
//        if (!FileUtil.saveFile(is, filePath)) {
//            return JSONUtil.errMessage();
//        }
//
//        if (content.getContenttype() == LeQiType.FELLOW_ENGLISH_BOOK) {
//
//            content.setIconPath(FileUtil.getRelationPath(filePath));
//            if (!this.contentDao.save(content)) {
//                return JSONUtil.errMessage();
//            }
//
//        } else {
//            content.setAudioPath(FileUtil.getRelationPath(filePath));
//            content.setContentLength(lenth);
//            String toPath = filePath.substring(0, filePath.length() - 3);
//            //FileUtil.unZip(filePath, toPath);
//            String textFilePath = toPath + "//leqi.txt";
//            // 读取所有文件的内容
//            String leqiText = FileUtil.readFile(textFilePath);
//            leqiText = leqiText.replace("\r\n", "<br/>&nbsp;&nbsp;");
//            leqiText = leqiText.replaceAll("\\[[\\d|\\:|\\.]*\\]",
//                    "<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
//            if (leqiText.length() > 300) {
//                content.setContent(leqiText.substring(0, 300));
//            } else {
//                content.setContent(leqiText);
//            }
//
//            if (!this.contentDao.save(content)) {
//                return JSONUtil.errMessage();
//            }
//
//            new WriteThread(content, leqiText, content.getId()).start();
//        }
//
//        return JSONUtil.okMessage();
//    }
    @Override
    public String createBook(Content content) {
        JSONObject json = new JSONObject();
        if (this.contentDao.save(content)) {
            json.put("msg", "ok");
            json.put("id", content.getId());
            return json.toString();
        } else {
            return JSONUtil.errMessage();
        }

    }

    @Override
    public String getContentListByCatalogId(Long cataLogId, PageParam page) {
        List<CatalogAndContent> list = this.getRelationShipDao().getCatalogAndContentListByCatalogId(cataLogId, page);
        List<Content> contentList = this.contentDao.getContentListByIds(this.getContentId(list));
        return this.makeContent2Json(contentList, page);
    }

    public List<Long> getContentId(List<CatalogAndContent> list) {
        List<Long> longList = new ArrayList<Long>();
        for (CatalogAndContent catalogAndContent : list) {
            longList.add(catalogAndContent.getContentId());
        }

        return longList;
    }

    @Override
    public String getMoreAritical(ContentTypeEnum type) {
        List ids = (List) EhcacheUtil.getContentData(EhcacheUtil.IDS);
        if (ids == null) {
            ids = this.contentDao.getContentIds(type);
            EhcacheUtil.putContentData(EhcacheUtil.IDS, ids);
        }
        return this.getContentByRandom(ids);
    }

    /**
     * 随机获取content
     *
     * @param list
     * @return
     */
    private String getContentByRandom(List<Long> list) {
        List<Long> ids = this.getIdList(list);
        List<Content> contents = new ArrayList<Content>();
        List<Long> newIds = new ArrayList<Long>();
        for (Long id : ids) {
            Content content = (Content) EhcacheUtil.getContentData(id);
            if (content == null) {
                newIds.add(id);
            } else {
                contents.add(content);
            }
        }

        if (!newIds.isEmpty()) {
            List<Content> newContents = this.contentDao.getContentListByIds(newIds);
            for (Content content : newContents) {
                EhcacheUtil.putContentData(content.getId(), content);
            }
            contents.addAll(this.contentDao.getContentListByIds(newIds));
        }

        try {
            // return JSONUtil.toJson(contents);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ContentBizImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return JSONUtil.errMessage();
    }

    private List<Long> getIdList(List<Long> list) {
        List<Long> ids = new ArrayList<Long>();
        int count = 3;
        while (true) {
            if (ids.size() == count) {
                break;
            }

            long index = (long) (Math.random() * list.size());
            if (!ids.contains(index)) {
                ids.add(index);
            }
        }

        return ids;
    }

//    @Override
//    public String updateAritical() {
//        List<Content> list = this.contentDao.getAllContent(null, 0);
//        for (Content content : list) {
//            try {
//
//                String text = FileUtil.readFile(content.getContentPath(),
//                        Integer.parseInt(content.getStartIndex() + ""),
//                        content.getContentLength());
//
//                String path = FileUtil.getPath();
//
//                FileChannel fc = new RandomAccessFile(path, "rw").getChannel();
//                fc.position(0); // 移到文件尾
//                fc.write(ByteBuffer.wrap(text.getBytes()));
//                fc.close();
//                content.setContentPath(path);
//                content.setStartIndex(0);
//                this.contentDao.updateContent(content);
//            } catch (IOException ex) {
//                java.util.logging.Logger.getLogger(ContentBizImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return JSONUtil.okMessage();
//    }
    /**
     * @return the relationShipDao
     */
    public RelationShipDao getRelationShipDao() {
        return relationShipDao;
    }

    /**
     * @param relationShipDao the relationShipDao to set
     */
    public void setRelationShipDao(RelationShipDao relationShipDao) {
        this.relationShipDao = relationShipDao;
    }

    @Override
    public String getNewContent(Long contentId, ContentTypeEnum type) {
        List contents = this.contentDao.getContents(type, contentId, OperatorEnum.GT);
        try {
            //return JSONUtil.toJson(contents);
        } catch (Exception ex) {
            Logger.getLogger(ContentBizImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return JSONUtil.errMessage();
    }

//    @Override
//    public String getMoreContent(PageParam page, Long contentId, int type) {
//        List contents = this.contentDao.getContents(page, type, contentId, OperatorEnum.LT);
//        try {
//            return JSONUtil.toJson(contents);
//        } catch (Exception ex) {
//            Logger.getLogger(ContentBizImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return JSONUtil.errMessage();
//    }
    @Override
    public String getAllContentByTypeAndCatalogs(PageParam page, ContentTypeEnum type, String catalogs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // this.
    }

    @Override
    public String hasTitle(String title) {
        Boolean hasTitle = this.contentDao.hasTitle(title);
        return JSONUtil.toJsonObject(hasTitle).toString();
    }

    @Override
    public String getMoreContent(PageParam page, Long contentId, ContentTypeEnum type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
