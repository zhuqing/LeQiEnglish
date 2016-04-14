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
 * @author guona
 */
@JSONClass
public class Word extends Content {

    /**
     * 过去式单词
     */
    @JSON(name = "pastWords")
    private String pastWords;
    /**
     * 完成式单词
     */
     @JSON(name = "hasWords")
    private String hasWords;

    /**
     * @return the pastWords
     */
    public String getPastWords() {
        return pastWords;
    }

    /**
     * @param pastWords the pastWords to set
     */
    public void setPastWords(String pastWords) {
        this.pastWords = pastWords;
    }

    /**
     * @return the hasWords
     */
    public String getHasWords() {
        return hasWords;
    }

    /**
     * @param hasWords the hasWords to set
     */
    public void setHasWords(String hasWords) {
        this.hasWords = hasWords;
    }

}
