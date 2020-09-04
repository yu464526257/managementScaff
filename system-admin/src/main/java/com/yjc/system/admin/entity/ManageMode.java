package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务表
 *
 * @author yjc
 * @date 2020-07-13 15:51:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_MODE")
public class ManageMode extends BaseEntity{
private static final long serialVersionUID = 1L;

  /**
   * $column.comments
   */
  @TableId
  @ApiModelProperty(value = "菜单id")
  private String id;
  /**
   * 模块名称
   */
  @ApiModelProperty(value = "模块名称")
  private String modeName;
  /**
   * 模块编码
   */
  @ApiModelProperty(value = "模块编码")
  private Integer modeCode;


}
