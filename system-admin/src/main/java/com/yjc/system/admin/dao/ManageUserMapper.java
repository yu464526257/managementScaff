package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yidi.system.admin.dto.NewAddUserDto;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.dto.UserDto;
import com.yidi.system.admin.entity.ManageUser;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 *
 * @author yjc
 * @date 2020-07-05 17:12:20
 */
public interface ManageUserMapper extends BaseMapper<ManageUser> {
  /**
    * 用户表简单分页查询
    * @param manageUser 用户表
    * @return
    */
  IPage<ManageUser> getManageUserPage(Page page, @Param("manageUser") ManageUser manageUser);


  void saveUserOld(@Param("user") ManageUser user);
  void addNewUser(@Param("user") NewAddUserDto user);

  void setOldUserHis(@Param("user") UpdateUserDto user);

  void updateUser(@Param("user") UpdateUserDto userDto);

  void updateUserPassword(@Param("user") ManageUser user);

  /**
   * 查询全部
   * @return
   */
  IPage<UserDto> getManageUser(Page page);

  /***
   * 根据用户编号查询用户信息
   * @param userId
   * @return
   */
  ManageUser getManageUserById(@Param("userId") String userId);

}
