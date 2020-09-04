package com.yjc.system.commen.common.utils;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/19
 * 所属功能
 */

import java.text.SimpleDateFormat;
import java.util.*;

public class UuidUtil {

    public static String getUuidFor32(){
        String oldUuidStr=UUID.randomUUID().toString();
        oldUuidStr=oldUuidStr.replace("-","");
        return oldUuidStr;
    }

    public static List<String> getUuidFor32List(Integer size){
        List<String> ids=new ArrayList<>();
        for(int i=0;i<size;i++){
            String oldUuidStr=UUID.randomUUID().toString();
            ids.add(oldUuidStr.replace("-",""));
        }
        return ids;
    }


    public static Integer getUuidFor13OrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        if(orderId.toString().length()>13){
            orderId=getUuidFor13OrderId();
        }
        return orderId;
    }

    /**
     * 生成11为随机编号
     * @return
     */
    public static String createRandomId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId+String.format("%06d", hashCodeV);
    }

    public  static String createRandom() {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }


}
