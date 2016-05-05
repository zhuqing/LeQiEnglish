package com.leqienglish.daoimpl;

import com.leqienglish.entity.Entity;
import java.util.List;

import com.leqienglish.entity.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class TampletDaoImpl<T extends Entity> {

    public abstract Class getEntityClass();

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 分页查询
     *
     * @param page
     * @param hql
     * @return
     */
    protected List<T> query(final Query query) {

        return  this.getMongoTemplate().find(query, this.getEntityClass());
    }
    

    
      /**
     * 分页查询
     *
     * @param page
     * @param hql
     * @return
     */
    public T query(Long id) {

        return (T) this.getMongoTemplate().findOne(this.getIdQuery(id), this.getEntityClass());
    }

    /**
     * 技术数量
     *
     * @param query
     * @return
     */
    protected long count(final Query query) {
        return this.getMongoTemplate().count(query, this.getEntityClass());
    }

    /**
     * 初始化查询的分页
     *
     * @param page
     * @param query
     */
    protected void setPageQuery(final PageParam page, final Query query) {
        int skip = (page.getCurrentPage() - 1) * page.getPageSize();
        query.skip(skip).limit(page.getPageSize());
    }

    /**
     * 获取所有
     *
     * @param hql
     * @return
     */
    public List queryAll() {
        return this.getMongoTemplate().findAll(this.getEntityClass());
    }
    
   
    
        /**
     * 获取所有
     *
     * @param hql
     * @return
     */
    public List queryAll(PageParam page) {
        Query query = new Query();
       this.setPageQuery(page, query);
        return this.getMongoTemplate().find(query, this.getEntityClass());
    }

    /**
     * 保存
     *
     * @param object
     * @return
     */
    public boolean save(T object) {
        try {
            this.getMongoTemplate().save(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected Query getIdQuery(Long id) {
        Criteria criteria = Criteria.where("id").is(id);
        return new Query(criteria);
    }

    /**
     * 更新
     *
     * @param query
     * @param update
     * @param t
     * @return
     */
    protected boolean update(final Query query, final Update update) {

        try {
            this.getMongoTemplate().updateMulti(query, update, this.getEntityClass());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 删除
     *
     * @param object
     * @return
     */
    public boolean delete(Long id) {

        try {
           
            this.mongoTemplate.remove(this.getIdQuery(id), this.getEntityClass());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
   public boolean delete(T t){
       return this.delete(t.getId());
   }

    /**
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * @param mongoTemplate the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
