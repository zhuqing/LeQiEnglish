package com.leqienglish.dao;

import com.leqienglish.entity.Content;
import com.leqienglish.entity.ContentTypeEnum;
import com.leqienglish.entity.Entity;
import com.leqienglish.entity.PageParam;
import com.leqienglish.util.operator.OperatorEnum;
import java.util.List;

public interface ContentDao<T extends Content> extends TempDao<T>{



    /**
     * 按照类型 分页获取内容
     *
     * @param page
     * @param type
     * @return
     */
    public List<T> getAllContent(PageParam page, ContentTypeEnum type);

    /**
     * 按照类型 分页获取内容
     *
     * @param page
     * @param type
     * @return
     */
    public List<T> getAllContenByParentId(PageParam page, Long parentId);


    /**
     * 获取改内容的上一个或下一个内容
     *
     * @param id
     * @param isup
     * @return
     */
    public Content getContentNextOrUp(Long id, boolean isup);

    /**
     * 修改评论数
     *
     * @param id
     * @param recoment
     * @return
     */
    public boolean addRecoment(T t);

    /**
     * 修改阅读数
     *
     * @param id
     * @param reader
     * @return
     */
    public boolean addReader(T t);



    /**
     * 根据Id列表获取所有的Content
     *
     * @param ids
     * @return
     */
    public List<Content> getContentListByIds(List<Long> ids);

    /**
     * 获取所有的ContentId
     *
     * @return
     */
    public List<Long> getContentIds(ContentTypeEnum type);

    public Boolean updateContent(T content);

    public List<T> getContents(ContentTypeEnum type, Long id, OperatorEnum operator);

    public List<T> getContents(PageParam page, int type, Long id, OperatorEnum operator);
    
    public Boolean hasTitle(String title);

}
