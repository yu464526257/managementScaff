package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.SysCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典表
 *
 * @author yjc
 * @date 2020-07-01 14:05:57
 */
public interface SysCodeMapper extends BaseMapper<SysCode> {
  /**
    * 字典表简单分页查询
    * @param sysCode 字典表
    * @return
    */
  IPage<SysCode> getSysCodePage(Page page, @Param("sysCode") SysCode sysCode);


  List<SysCode> getSysCodePage(@Param("sysCode") SysCode sysCode);


  Integer getSequenceIdNextVal();


}
