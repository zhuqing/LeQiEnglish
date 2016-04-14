/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leqienglish.dao;

import com.leqienglish.entity.Content;
import com.leqienglish.entity.Word;

/**
 *
 * @author guona
 */
public interface DictDao {
    /**
     * 根据单词查找单词
     * @param word
     * @return 
     */
    public Content findWords(String word);
    public void saveContent(Content content);
}
