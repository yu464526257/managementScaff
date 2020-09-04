package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageUserModePower;


import java.util.List;

/**
 * 用户业务数据权限关联表
 *
 * @author yjc
 * @date 2020-07-05 18:58:42
 */
public interface ManageUserModePowerService extends IService<ManageUserModePower> {

  /**
   * 用户业务数据权限关联表简单分页查询
   * @param manageUserModePower 用户业务数据权限关联表
   * @return
   */
  IPage<ManageUserModePower> getManageUserModePowerPage(Page<ManageUserModePower> page, ManageUserModePower manageUserModePower);

  void delModePower(UpdateUserDto updateUserDto);

  /**
   * 查询用户相关业务
   * @param userId
   * @return
   */
  ResultEntity getManageModeUserList(String userId);

  /**
   * 删除单条用户相关业务
   * @param modeCode
   * @param powerCode
   * @param userId
   * @return
   */
  ResultEntity delManageModeUser(int modeCode, int powerCode, String userId);

  /**
   * 删除用户相关所有业务
   * @param id
   * @return
   */
  ResultEntity delAllManageModeUser(String id);

  /**
   * 修改用户相关业务
   * @param userId
   * @param list
   * @return
   */
  ResultEntity updateUserMode(String userId, List<ManageUserModePower> list);

  ResultEntity addUserMode(List<ManageUserModePower> list);
}
