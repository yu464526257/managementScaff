package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色跟老得岗位对应
 *
 * @author yjc
 * @date 2020-07-10 15:50:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_ROLE_MENU")
public class ManageRoleMenu extends Model<ManageRoleMenu> {
private static final long serialVersionUID = 1L;

  /**
   * 菜单url
   */
  @ApiModelProperty(value = "菜单url")
  private String url;
  /**
   * 角色ID
   */
  @ApiModelProperty(value = "角色ID")
  private String roleId;
  /**
   * 菜单ID
   */
  @TableId
  @ApiModelProperty(value = "菜单ID")
  private Long menuId;
  /**
   * 角色名称
   */
  @ApiModelProperty(value = "角色名称")
  private String roleName;
  /**
   * 菜单名称
   */
  @ApiModelProperty(value = "菜单名称")
  private String menuName;

  @Override
  protected Serializable pkVal() {
    return this.menuId;
  }
}
