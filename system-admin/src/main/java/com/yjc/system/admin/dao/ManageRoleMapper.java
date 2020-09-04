package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.dto.ManageRoleDto;
import com.yjc.system.admin.entity.ManageRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:46:14
 */
@Mapper
public interface ManageRoleMapper extends BaseMapper<ManageRole> {
  /**
    * ${comments}简单分页查询
    * @param manageRoleDto ${comments}
    * @return
    */
  IPage<ManageRoleDto> getManageRolePage(Page page, @Param("manageRole") ManageRoleDto manageRoleDto);

  /**
   * 查询全部角色
   * @return
   */
  List<ManageRole> getManageRoleList();

  /**
   * 根据角色编号查询角色信息
   * @author zyt
   * @param roleId
   * @return
   */
  ManageRole getManageRoleById(@Param("roleId") String roleId);

  /**
   * 新增角色
   * @param manageRole
   * @return
   */
  Integer addManageRole(@Param("manageRole") ManageRole manageRole);

  /**
   * 修改角色信息
   * @param manageRole
   * @return
   */
  Integer updateManageRole(@Param("manageRole") ManageRole manageRole);

  /**
   * 根据角色id删除角色
   * @param roleId  角色id
   * @return
   */
  Integer deleteManageRole(@Param("roleId") String roleId,@Param("updateUserId") String updateUserId);
}
