package com.yjc.system.commen.common.utils;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/6
 * 所属功能
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSpecial {

    public static String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";


    /**
     * 判断是否含有特殊字符
     *
     * true为包含，false为不包含
     */
    public static  boolean stringCheck(String value){
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(value);
        return m.find();
    }

    public static  boolean moneyCheck(BigDecimal money){
        BigDecimal b =new BigDecimal("0");
        int i=money.compareTo(b);
        if(i>0){
            return false;
        }
        return true;
    }

    public static  boolean listCheck(List list){
        if(list != null && list.size()>0){
            return true;
        }
        return false;
    }


}
