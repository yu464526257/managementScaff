package com.yjc.system.admin.dto;

import com.yjc.system.admin.entity.ManageRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HuRongHua
 * @date 2020/7/13 10:56 上午
 */
@Data
public class ManageRoleDto extends ManageRole {

    /**
     * 角色状态Name:0正常,1禁用
     */
    @ApiModelProperty(value = "角色状态:0正常,1禁用")
    private String roleStatusCodeName;
}
