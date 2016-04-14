///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.leqienglish.bizimpl;
//
//import com.leqienglish.biz.DictBiz;
//import com.leqienglish.dao.ContentDao;
//import com.leqienglish.dao.DictDao;
//import com.leqienglish.dao.RelationShipDao;
//import com.leqienglish.entity.Content;
//import com.leqienglish.entity.RelationShip;
//import com.leqienglish.util.FileUtil;
//import com.leqienglish.util.LeQiType;
//import com.leqienglish.util.Util;
//import com.leqienglish.util.json.JSONUtil;
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import net.sf.json.JSONObject;
//
///**
// *
// * @author guona
// */
//public class DictBizImpl implements DictBiz {
//
//    private DictDao dictDao;
//    private RelationShipDao relationShipDao;
//    private ContentDao contentDao;
//
//    /**
//     * @return the dictDao
//     */
//    public DictDao getDictDao() {
//        return dictDao;
//    }
//
//    /**
//     * @param dictDao the dictDao to set
//     */
//    public void setDictDao(DictDao dictDao) {
//        this.dictDao = dictDao;
//    }
//
//    @Override
//    public String fetchDicts(String words) {
//        try {
//            Pattern pa = Pattern.compile("<(.[^>]*)>");
//            Matcher matcher = pa.matcher(words);
//            String suncont = matcher.replaceAll(" ");
//            //replace(/&nbsp;/ig, ""); 
//            suncont = suncont.replaceAll("&nbsp;", " ");
//
//            String[] strArr = suncont.split(" ");
//            Set<String> wordsSet = new HashSet<String>();
//            for (String word : strArr) {
//                word = FileUtil.removeUnChar(word);
//                System.err.println(word);
//                if (word == null || word.isEmpty()) {
//                    continue;
//                }
//                if (!wordsSet.contains(word.trim())) {
//                    wordsSet.add(word.trim());
//                }
//            }
//            List<Content> list = new ArrayList<Content>();
//            for (String word : wordsSet) {
//                Content content = this.getDictDao().findWords(word);
//                if (content != null) {
//                    list.add(content);
//                } else {
//                    // URL downLoad;
//                    try {
//                        URL downLoad = new URL("http://openapi.baidu.com/public/2.0/translate/dict/simple?client_id=wYFruBcLqKTRfCbXeQNWQ72w&from=en&to=zh&q=" + word);
//
//                        HttpURLConnection ucon = (HttpURLConnection) downLoad.openConnection();
//                        ucon.setRequestMethod("GET");
//                        ucon.setConnectTimeout(5000);
//
//                        InputStream is = ucon.getInputStream();
//                        BufferedInputStream bis = new BufferedInputStream(is);
//
//                        int code = ucon.getResponseCode();
//                        if (code == HttpURLConnection.HTTP_OK) {
//                            byte[] by = new byte[bis.available()];
//                            bis.read(by);
//                            String str = new String(by);
//                            JSONObject json = JSONObject.fromObject(str);
//                            if (!json.get("errno").toString().equals("0")) {
//                                continue;
//                            }
//                            Content wordContent = new Content();
//                            wordContent.setTitle(word);
//                            wordContent.setContenttype(LeQiType.WORD);
//                            wordContent.setContent(str);
//                            wordContent.setId(Util.getID());
//                            wordContent.setCreateTime(Calendar.getInstance().getTimeInMillis() + "");
//                            list.add(wordContent);
//                            this.getDictDao().saveContent(wordContent);
//                        }
//
//                    } catch (Exception ex) {
//                        Logger.getLogger(DictBizImpl.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//            return JSONUtil.toJson(list);
//        } catch (Exception ex) {
//            Logger.getLogger(DictBizImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return JSONUtil.errMessage();
//    }
//
//    @Override
//    public String saveWordsContent(Long contentId, String words) {
//        String[] wordArr = words.split(";");
//        for (String wordIdStr : wordArr) {
//            if(wordIdStr.isEmpty()){
//                continue;
//            }
//            try {
//                Long wordId = Long.valueOf(wordIdStr);
//                RelationShip relationShip = new RelationShip();
//                relationShip.setSourceId(contentId);
//                relationShip.setTargetId(wordId);
//                relationShip.setRelationShipType(LeQiType.WORD);
//                relationShip.setCreateTime(Calendar.getInstance().getTimeInMillis() + "");
//                this.getRelationShipDao().save(relationShip);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return JSONUtil.okMessage();
//    }
//
//    /**
//     * @return the relationShipDao
//     */
//    public RelationShipDao getRelationShipDao() {
//        return relationShipDao;
//    }
//
//    /**
//     * @param relationShipDao the relationShipDao to set
//     */
//    public void setRelationShipDao(RelationShipDao relationShipDao) {
//        this.relationShipDao = relationShipDao;
//    }
//
//    @Override
//    public String fetchWords(Long contentId) {
//        try {
//            List<RelationShip> list = this.getRelationShipDao().getRelationShipBySourceIdAndType(contentId, LeQiType.WORD);
//            List<Long> ids = new ArrayList<Long>();
//            for(RelationShip relationShip : list){
//                ids.add(relationShip.getTargetId());
//            }
//            List<Content> words = this.getContentDao().getContentListByIds(ids);
//            return JSONUtil.toJson(words);
//        } catch (Exception ex) {
//            Logger.getLogger(DictBizImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return JSONUtil.errMessage();
//    }
//
//    /**
//     * @return the contentDao
//     */
//    public ContentDao getContentDao() {
//        return contentDao;
//    }
//
//    /**
//     * @param contentDao the contentDao to set
//     */
//    public void setContentDao(ContentDao contentDao) {
//        this.contentDao = contentDao;
//    }
//
//}
