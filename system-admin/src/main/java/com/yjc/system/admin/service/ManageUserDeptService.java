package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.entity.ManageUserDept;


/**
 * 用户部门关联表
 *
 * @author yjc
 * @date 2020-07-10 15:12:00
 */
public interface ManageUserDeptService extends IService<ManageUserDept> {

  /**
   * 用户部门关联表简单分页查询
   * @param manageUserDept 用户部门关联表
   * @return
   */
  IPage<ManageUserDept> getManageUserDeptPage(Page<ManageUserDept> page, ManageUserDept manageUserDept);


  boolean inOrUpdateUserDept(UpdateUserDto updateUserDto);


}
