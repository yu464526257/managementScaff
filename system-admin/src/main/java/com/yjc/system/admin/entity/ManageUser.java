package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 *
 * @author yjc
 * @date 2020-07-05 17:12:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_USER")
public class ManageUser extends BaseEntity {
private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId
  @ApiModelProperty(value = "id")
  private String id;
  /**
   * 用户编号
   */
  @ApiModelProperty(value = "用户编号")
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
  @NotNull(message = "登录账户不能为空")
  private String password;
  /**
   * 用户名称
   */
  @ApiModelProperty(value = "用户名称")
  private String userName;
  /**
   * 用户性别
   */
  @ApiModelProperty(value = "用户性别")
  private Integer sex;

  /**
   * 角色id
   */
  @ApiModelProperty(value = "角色id")
  private String roleId;
  /**
   * 部门id
   */
  @ApiModelProperty(value = "部门id")
  private String departmentId;
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

  @Override
  protected Serializable pkVal() {
    return this.id;
  }

  /**
   * 用户新系统 业务权限关联表
   */
  @TableField(exist = false)
  private List<ManageUserModePower> powerList;
}
