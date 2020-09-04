package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yidi.system.admin.entity.ManageUser;
import com.yjc.system.admin.dto.LoginUserDto;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.dto.UserChangePasswordDto;
import com.yjc.system.admin.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户表
 *
 * @author yjc
 * @date 2020-07-05 17:12:20
 */
public interface ManageUserService extends IService<ManageUser> {

  /**
   * 用户表简单分页查询
   * @param manageUser 用户表
   * @return
   */
  IPage<ManageUser> getManageUserPage(Page<ManageUser> page, ManageUser manageUser);

    /**
     * 登录
     * @param user
     * @return
     */
   ResultEntity loginOld(Map<String, String> user);
    /**
     * 登录
     * @param user
     * @return
     */
   ResultEntity login(LoginUserDto user, HttpServletRequest request, HttpServletResponse response);


    /**
     * 新增
     * @param userDto
     * @return
     */
   ResultEntity addNewUser(NewAddUserDto userDto);

    /**
     * 修改
     * @param userDto
     * @return
     */
   ResultEntity updateUser(UpdateUserDto userDto);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    UserDto getUserById(String id);

 /**
  * 获取用户信息
  * @return
  */
 UserDto getUserInfo(HttpServletRequest request);

    /**
     * 修改密码
     * @param updateUserDto
     * @return
     */
    ResultEntity updateUserPassword(UserChangePasswordDto updateUserDto);

    /**
     * 修改用户角色关联信息
     * @param userDto
     * @return
     */
    ResultEntity updateUserRoles(UpdateUserDto userDto);

    /**
     * 修改用户业务权限表
     * @param userDto
     * @return
     */
    ResultEntity updateUserModel(UpdateUserDto userDto);

    /**
     * 查询全部用户信息
     * @return
     */
    ResultEntity getManageUser(Page page);


}
