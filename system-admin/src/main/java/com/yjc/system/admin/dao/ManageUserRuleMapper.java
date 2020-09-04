package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.ManageUserRule;
import org.apache.ibatis.annotations.Param;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:36:08
 */
public interface ManageUserRuleMapper extends BaseMapper<ManageUserRule> {
  /**
    * ${comments}简单分页查询
    * @param manageUserRule ${comments}
    * @return
    */
  IPage<ManageUserRule> getManageUserRulePage(Page page, @Param("manageUserRule") ManageUserRule manageUserRule);

  /**
   * 增加用户和菜单管理
   * @param manageUserRule
   * @return
   */
  Integer addUserRule(@Param("manageUserRule") ManageUserRule manageUserRule);


}
