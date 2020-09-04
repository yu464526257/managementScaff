package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 数据权限表
 *
 * @author yjc
 * @date 2020-07-13 17:21:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_DATA_POWER")
public class ManageDataPower extends Model<ManageDataPower> {
private static final long serialVersionUID = 1L;

  /**
   * 表id
   */
  @TableId
  @ApiModelProperty(value = "表id")
  private String id;
  /**
   * 权限编号
   */
  @ApiModelProperty(value = "权限编号")
  private Integer powerCode;
  /**
   * 权限名称
   */
  @ApiModelProperty(value = "权限名称")
  private String powerName;
  /**
   * 所属业务编号
   */
  @ApiModelProperty(value = "所属业务编号")
  private Integer byModeCode;
  /**
   * 权限级别
   */
  @ApiModelProperty(value = "权限级别")
  private Integer powerLever;
  /**
   * 拥有数据权限内容
   */
  @ApiModelProperty(value = "拥有数据权限内容")
  private String havaDatapower;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
