package com.yjc.system.admin.dto;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/8
 * 所属功能
 */

import com.yjc.system.admin.entity.BaseEntity;
import com.yjc.system.admin.entity.ManageUserModePower;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class UpdateUserDto extends BaseEntity {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private String id;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @NotNull(message = "用户编号不能为空")
    private Long userNo;
    /**
     * 用户登录账号
     */
    @ApiModelProperty(value = "用户登录账号")
    @NotNull(message = "登录账户不能为空")
    private String userLoginNo;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空")
    private String password;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    @NotNull(message = "用户名称不能为空")
    private String userName;
    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private Integer sex=0;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private List<String> roleIds;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    @NotNull(message = "用户部门不能为空")
    private String departmentId;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "次要部门id")
    private String secondaryDepartmentIds;
    /**
     * 平台id
     */
    @ApiModelProperty(value = "平台id")
    private String platformId;
    /**
     * 平台id
     */
    @ApiModelProperty(value = "照片id")
    private String photoId;


    @ApiModelProperty(value = "新建历史表id")
    private String hisId;

    @ApiModelProperty(value = "用户业务权限")
    private List<ManageUserModePower> userModePowers;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
