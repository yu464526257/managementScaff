package com.yjc.system.commen.common.enums;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/27
 * 所属功能
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HistoryTypeEnum {

    NEW(1,"新增"),
    CHANGE(2,"修改");


    private Integer code;
    private String desc;
}
