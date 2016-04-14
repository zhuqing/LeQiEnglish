/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.entity;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;

/**
 *
 * @author zhuleqi
 */
@JSONClass()
public class LeqiDTO {

    @JSON(name = "page")
    private PageParam page;
    @JSON(name = "value")
    private Object value;

    /**
     * @return the page
     */
    public PageParam getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(PageParam page) {
        this.page = page;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
    
}
