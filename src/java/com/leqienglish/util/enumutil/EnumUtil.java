/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.util.enumutil;

import com.leqienglish.entity.ContentTypeEnum;

/**
 *
 * @author zhuleqi
 */
public class EnumUtil {
     public static boolean hasEnum(Class enumClass , String type){
       if(!enumClass.isEnum()){
           return false;
       }
       Object[] enums = enumClass.getEnumConstants();
       for(Object item : enums){
           if(item.toString().equals(type)){
               return true;
           }
       }
      
        return false;
    }
}
