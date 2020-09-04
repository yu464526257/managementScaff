package com.yjc.system.commen.common.enums;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/30
 * 所属功能
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  IsUpEnum {


    YES(1,"上架"),
    NO(2,"下架");

    private int code;
    private String desc;
}
