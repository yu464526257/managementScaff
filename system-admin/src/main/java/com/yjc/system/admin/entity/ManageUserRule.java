package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:36:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_USER_RULE")
public class ManageUserRule extends Model<ManageUserRule> {
private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId
  @ApiModelProperty(value = "id")
  private String userId;
  /**
   * 用户编号
   */
  @ApiModelProperty(value = "用户编号")
  private Long userNo;
  /**
   * 用户登录账号
   */
  @ApiModelProperty(value = "用户登录账号")
  private String userLoginNo;
  /**
   * 用户名称
   */
  @ApiModelProperty(value = "用户名称")
  private String userName;
  /**
   * 角色ID
   */
  @ApiModelProperty(value = "角色ID")
  private String roleId;
  /**
   * 角色名称
   */
  @ApiModelProperty(value = "角色名称")
  private String roleName;

  @Override
  protected Serializable pkVal() {
    return null;
  }
}
