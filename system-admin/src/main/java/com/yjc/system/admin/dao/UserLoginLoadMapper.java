package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.UserLoginLoad;
import org.apache.ibatis.annotations.Param;

/**
 * 用户登录记录表


 *
 * @author yjc
 * @date 2020-06-29 13:45:28
 */
public interface UserLoginLoadMapper extends BaseMapper<UserLoginLoad> {
  /**
    * 用户登录记录表

简单分页查询
    * @param userLoginLoad 用户登录记录表


    * @return
    */
  IPage<UserLoginLoad> getUserLoginLoadPage(Page page, @Param("userLoginLoad") UserLoginLoad userLoginLoad);


}
