package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.entity.UserLoginLoad;

/**
 * 用户登录记录表


 *
 * @author yjc
 * @date 2020-06-29 13:45:28
 */
public interface UserLoginLoadService extends IService<UserLoginLoad> {

  /**
   * 用户登录记录表

简单分页查询
   * @param userLoginLoad 用户登录记录表


   * @return
   */
  IPage<UserLoginLoad> getUserLoginLoadPage(Page<UserLoginLoad> page, UserLoginLoad userLoginLoad);





}
