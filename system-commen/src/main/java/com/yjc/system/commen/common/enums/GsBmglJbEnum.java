package com.yjc.system.commen.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  GsBmglJbEnum {
    MINISTRY(1, "部"),
    PLACE(2, "处"),
    SECTION(3, "科"),
    ROOM(4, "室"),
    GROUP(5, "组");


    private Integer code;
    private String desc;
}
