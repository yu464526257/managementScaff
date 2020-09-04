package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.entity.ManageUserRule;


/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:36:08
 */
public interface ManageUserRuleService extends IService<ManageUserRule> {

  /**
   * ${comments}简单分页查询
   * @param manageUserRule ${comments}
   * @return
   */
  IPage<ManageUserRule> getManageUserRulePage(Page<ManageUserRule> page, ManageUserRule manageUserRule);


  boolean inOrUpdate(UpdateUserDto updateUserDto);




}
