package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.UserLoginLoadMapper;
import com.yjc.system.admin.entity.UserLoginLoad;
import com.yjc.system.admin.service.UserLoginLoadService;
import org.springframework.stereotype.Service;

/**
 * 用户登录记录表


 *
 * @author yjc
 * @date 2020-06-29 13:45:28
 */
@Service("userLoginLoadService")
public class UserLoginLoadServiceImpl extends ServiceImpl<UserLoginLoadMapper, UserLoginLoad> implements UserLoginLoadService {

  /**
   * 用户登录记录表

简单分页查询
   * @param userLoginLoad 用户登录记录表


   * @return
   */
  @Override
  public IPage<UserLoginLoad> getUserLoginLoadPage(Page<UserLoginLoad> page, UserLoginLoad userLoginLoad){
      return baseMapper.getUserLoginLoadPage(page,userLoginLoad);
  }

}
