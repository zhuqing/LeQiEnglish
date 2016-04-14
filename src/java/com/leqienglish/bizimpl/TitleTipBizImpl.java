/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leqienglish.bizimpl;

import com.leqienglish.biz.TitleTipBiz;
import com.leqienglish.dao.TitleTipDao;
import com.leqienglish.util.json.JSONUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guona
 */
public class TitleTipBizImpl implements TitleTipBiz {

    private TitleTipDao titleTipDao;
    @Override
    public String getTitleTipBiz() {
        List list = this.getTitleTipDao().getTitleTips();
        try {
            //return JSONUtil.toJson(list);
        } catch (Exception ex) {
            Logger.getLogger(TitleTipBizImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return JSONUtil.errMessage();
    }

    /**
     * @return the titleTipDao
     */
    public TitleTipDao getTitleTipDao() {
        return titleTipDao;
    }

    /**
     * @param titleTipDao the titleTipDao to set
     */
    public void setTitleTipDao(TitleTipDao titleTipDao) {
        this.titleTipDao = titleTipDao;
    }
    
}
