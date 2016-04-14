/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leqienglish.biz; 

/**
 *
 * @author guona
 */
public interface DictBiz {
    public String fetchDicts(String words);
    
    /**
     * 保存word 和 content的关系
     * @param contentId
     * @param words
     * @return 
     */
    public String saveWordsContent(Long contentId , String words);
    /**
     * 取Content相关的单词
     * @param contentId
     * @return 
     */
    public String fetchWords(Long contentId);
}
