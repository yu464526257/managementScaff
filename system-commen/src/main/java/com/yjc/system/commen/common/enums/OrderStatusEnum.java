package com.yjc.system.commen.common.enums;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/24
 * 所属功能
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    NEWSAVE(1, "新增"),
    CANCEL(2, "取消"),
    COMMIT(3, "已生成");


    private Integer code;
    private String desc;
}
