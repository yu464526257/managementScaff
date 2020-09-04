package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageDataPower;
import com.yjc.system.admin.entity.ManageMode;

import java.util.List;

/**
 * 数据权限表
 *
 * @author yjc
 * @date 2020-07-13 17:21:28
 */
public interface ManageDataPowerService extends IService<ManageDataPower> {

  /**
   * 数据权限表简单分页查询
   * @param manageDataPower 数据权限表
   * @return
   */
  IPage<ManageDataPower> getManageDataPowerPage(Page<ManageDataPower> page, ManageDataPower manageDataPower);

  /**
   * 根据业务代码集合查询数据权限
   * @param modes  业务编码
   * @return
   */
  List<ManageDataPower> selectListByModeList(List<ManageMode> modes);

  ResultEntity addManageDataPower(ManageDataPower manageDataPower);

  ResultEntity addAllManageDataPower(List<ManageDataPower> manageDataPower);

  ResultEntity editManageDataPower(List<ManageDataPower> manageDataPower);

  ResultEntity delManageDataPower(String id);

  ResultEntity delUserManageDataPower(int modeCode);

}
