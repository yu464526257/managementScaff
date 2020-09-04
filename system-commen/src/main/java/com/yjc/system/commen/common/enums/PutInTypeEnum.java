package com.yjc.system.commen.common.enums;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/2
 * 所属功能
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  PutInTypeEnum {
    PUT_IN(1, "新增入库"),
    VERIFY_IN(2, "平库入库");


    private Integer code;
    private String desc;

}
