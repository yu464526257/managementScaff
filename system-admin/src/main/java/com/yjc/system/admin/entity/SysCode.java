package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典表
 *
 * @author yjc
 * @date 2020-07-01 14:05:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("SYS_CODE")
public class SysCode extends Model<SysCode> {
private static final long serialVersionUID = 1L;

  /**
   * ID编号
   */
  @TableId
  @ApiModelProperty(value = "ID编号")
  private String id;
  /**
   * 编码值
   */
  @ApiModelProperty(value = "编码值")
  private Integer code;
  /**
   * 编码内容
   */
  @ApiModelProperty(value = "编码内容")
  private String name;
  /**
   * 编码类型
   */
  @ApiModelProperty(value = "编码类型")
  private String type;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
