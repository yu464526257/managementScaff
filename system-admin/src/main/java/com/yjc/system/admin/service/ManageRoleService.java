package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.ManageRoleDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageRole;

import javax.servlet.http.HttpServletRequest;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:46:14
 */
public interface ManageRoleService extends IService<ManageRole> {

  /**
   * ${comments}简单分页查询
   * @param manageRoleDto ${comments}
   * @return
   */
  IPage<ManageRoleDto> getManageRolePage(Page<ManageRoleDto> page, ManageRoleDto manageRoleDto);

  /**
   * 查询全部角色
   * @return
   */
  ResultEntity getManageRoleList();

  /**
   * 根据角色编号查询角色
   * @param roleId
   * @return
   */
  ResultEntity getManageRoleByRoleId(String roleId);


  /**
   * 保存
   * @param manageRole
   * @return
   */
  ResultEntity save(ManageRole manageRole, HttpServletRequest request);

  /**
   * 修改
   * @param manageRole
   * @return
   */
  ResultEntity update(ManageRole manageRole,HttpServletRequest request);

  /**
   * 删除角色
   * @param roleId  角色id
   * @return
   */
  ResultEntity delete(String roleId,HttpServletRequest request);
}
