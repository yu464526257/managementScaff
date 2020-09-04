package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.SysCode;


import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author yjc
 * @date 2020-07-01 14:05:57
 */
public interface SysCodeService extends IService<SysCode> {

  /**
   * 字典表简单分页查询
   * @param sysCode 字典表
   * @return
   */
  IPage<SysCode> getSysCodePage(Page<SysCode> page, SysCode sysCode);

  /**
   * 根据类型获取code/Name
   * @param sysCode
   * @return
   */
  ResultEntity codeListByType(SysCode sysCode);

  List<SysCode> codeListByType(String type);

  Map<Integer,String> codeMapByType(String type);


  Integer getSequenceIdNextVal();



}
