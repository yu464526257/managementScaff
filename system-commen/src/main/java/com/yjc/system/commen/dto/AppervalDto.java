package com.yjc.system.commen.dto;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/29
 * 所属功能
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AppervalDto implements Serializable {

    private String canteenApplyTicketId;

    private String departId;

    private String partId;

    private Integer applyStatus;

    private String approvalUser;

    private String approvalPhone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
