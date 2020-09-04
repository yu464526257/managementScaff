package com.yjc.system.commen.common.enums;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/28
 * 所属功能
 */

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ApplyTicket {


        APPLY(0,"申请"),
        EXAMINE_RUN(1,"审批-通过"),
        EXAMINE_BACK(2,"审批-退回"),
        VERIFY_RUN(3,"审核-通过"),
        VERIFY_BACK(4,"审核-退回"),
        OVER(5,"通过");


        private Integer code;
        private String desc;




}
