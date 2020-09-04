package com.yjc.system.commen.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Json工具类
* @author YJC
* @description 
**/
public class JsonUtils {
    private static final Logger logger =LoggerFactory.getLogger(JsonUtils.class);

    public static JSONArray getJsonVauleArray(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getJSONArray(key);
        }
        return null;
    }
    public static JSONObject getJsonVauleJson(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getJSONObject(key);
        }

        return null;
    }
    public static Date getJsonVauleDate(JSONObject json, String key){
        if(json.containsKey(key)&&StringUtils.isNotEmpty(json.getString(key))){
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.getString(key));
            } catch (ParseException e) {
                logger.error("json日期转换异常");
            }
        }

        return null;
    }
    public static String getJsonVauleStr(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getString(key);
        }

        return null;
    }
    public static Integer getJsonVauleInt(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getInteger(key);
        }

        return 0;
    }
    public static Double getJsonVauleDou(JSONObject json, String key){
        if(json.containsKey(key)){
            return json.getDouble(key);
        }
        return 0.0;
    }

    /**
     * 判断是否json
     * @param test
     * @return
     */
    public final static boolean isJsonValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
