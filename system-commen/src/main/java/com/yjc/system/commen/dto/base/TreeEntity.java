package com.yjc.system.commen.dto.base;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/13
 * 所属功能 所有树的基础类型
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class TreeEntity implements Serializable {

    private String name;
    private String code;
    private Integer index;
    private Integer checked;
}
