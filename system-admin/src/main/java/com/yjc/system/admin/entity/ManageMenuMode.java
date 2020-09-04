package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 菜单业务关联表
 *
 * @author yjc
 * @date 2020-07-13 15:37:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_MENU_MODE")
public class ManageMenuMode extends Model<ManageMenuMode> {
private static final long serialVersionUID = 1L;

  /**
   * 表id
   */
  @TableId
  @ApiModelProperty(value = "表id")
  private String id;
  /**
   * 业务编码
   */
  @ApiModelProperty(value = "业务编码")
  private Integer modeCode;
  /**
   * 菜单ID
   */
  @ApiModelProperty(value = "菜单ID")
  private Long menuId;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
