package com.leqienglish.daoimpl;

import java.util.List;

import com.leqienglish.dao.ContentDao;
import com.leqienglish.entity.Content;
import com.leqienglish.entity.ContentTypeEnum;
import com.leqienglish.entity.PageParam;
import com.leqienglish.util.operator.OperatorEnum;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class ContentDaoImpl<T extends Content> extends TampletDaoImpl<T> implements ContentDao<T> {

    @Override
    public Class getEntityClass() {
        return Content.class;
    }

    @Override
    public List<T> getAllContenByParentId(PageParam page, Long parentId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("parentId").is(parentId);
        query.addCriteria(criteria);
        this.setPageQuery(page, query);
        return this.query(query);
    }

    @Override
    public Content getContentNextOrUp(Long id, boolean isup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addRecoment(T t) {
         Update update = new Update();
        update.set("recomment", t.getRecomment());
        return this.update(getQuery(t.getId()), update);
    }

    @Override
    public boolean addReader(T t) {
        Update update = new Update();
        update.set("reader", t.getReader());
        return this.update(getQuery(t.getId()), update);
    }

    @Override
    public List<Content> getContentListByIds(List<Long> ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateContent(T content) {
        Update update = new Update();

        return this.update(getQuery(content.getId()), update);
    }

    private Query getQuery(Long id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        return query;
    }

    @Override
    public List<T> getContents(PageParam page, int type, Long id, OperatorEnum operator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean hasTitle(String title) {
        Query query = new Query();
        Criteria criteria = Criteria.where("title").is(title);
        query.addCriteria(criteria);
        return this.count(query) > 0;
    }

    @Override
    public List<T> getAllContent(PageParam page, ContentTypeEnum type) {
        Query query = new Query();
        Criteria criteria = Criteria.where("contentType").is(type);
        query.addCriteria(criteria);
        this.setPageQuery(page, query);
        return this.query(query);
    }

    @Override
    public List<Long> getContentIds(ContentTypeEnum type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> getContents(ContentTypeEnum type, Long id, OperatorEnum operator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
