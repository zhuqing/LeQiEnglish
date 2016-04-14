package com.leqienglish.biz;

import java.io.InputStream;

import com.leqienglish.entity.Comment;
import com.leqienglish.entity.Content;
import com.leqienglish.entity.ContentTypeEnum;
import com.leqienglish.entity.PageParam;

public interface ContentBiz {

    /**
     * 保存内容
     *
     * @param content
     * @param neiRong
     * @param type
     * @return
     */
    public String saveContent(Content content);

    /**
     * 获取所有内容
     *
     * @param page
     * @return
     */
    public String getAllContent(PageParam page, Long userId);

    /**
     * 根据类型获取类容
     *
     * @param page
     * @param type
     * @return
     */
    public String getAllContentByType(PageParam page, ContentTypeEnum type, Long userId);

    /**
     * 根据类型和分类获取Content
     *
     * @param page
     * @param type
     * @param catalogs
     * @return
     */
    public String getAllContentByTypeAndCatalogs(PageParam page, ContentTypeEnum type, String catalogs);

    /**
     * 根据类型获取类容
     *
     * @param page
     * @param type
     * @return
     */
    public String getAllContentByParentId(PageParam page, Long contentId, Long userId);

    /**
     * 获取内容的详细信息
     *
     * @param id
     * @param type
     * @return
     */
    public String getContent(Long id);

    /**
     * 获取该Id上一条信息和下一条信息
     *
     * @param id
     * @param type
     * @return
     */
    public String getContentNextAndUp(Long id, ContentTypeEnum type);

    /**
     * 通过分类Id获取文章列表
     *
     * @param cataLogId
     * @param page
     * @return
     */
    public String getContentListByCatalogId(Long cataLogId, PageParam page);

    public String uploadFile(InputStream is, String path);

//    public String uploadFellowEnglish(Content content, InputStream is, String fileName);

    public String getTitleTip();

//    public String saveComment(Comment comment);
//
//    public String getComment(PageParam page, Long contentId);
//
//    public String getCommentById(long id);

    public String updateRecomment(long id, int recomment);

    public String updateReader(long id, int reader);

    /**
     * 删除Content by Id
     *
     * @param id
     * @param userid
     * @return
     */
    public String deleteContent(long id);

    /**
     * 创建课程
     *
     * @param content
     * @return
     */
    public String createBook(Content content);

    /**
     * 获取更多的课程
     *
     * @return
     */
    public String getMoreAritical(ContentTypeEnum type);

    /**
     * 获取新的Contentlist
     *
     * @param contentId
     * @param type
     * @return
     */
    public String getNewContent(Long contentId, ContentTypeEnum type);

    public String getMoreContent(PageParam page, Long contentId, ContentTypeEnum type);
    
    public String hasTitle(String title);

      //  public String updateAritical();
}
