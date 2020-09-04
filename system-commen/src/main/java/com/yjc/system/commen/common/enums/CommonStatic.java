package com.yjc.system.commen.common.enums;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/21
 * 所属功能 静态变量
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonStatic {

    public static  Integer canteenCode;
    public static  String  systemEditionCode;


    @Value("${system.service.code}")
    public void setCanteenCode(Integer canteenCode) {
        CommonStatic.canteenCode = canteenCode;
    }

    @Value("${system.edition.code}")
    public void set(String systemEditionCode) {
        CommonStatic.systemEditionCode = systemEditionCode;
    }

}
