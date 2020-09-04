package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageUserRuleMapper;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.entity.ManageRole;
import com.yjc.system.admin.entity.ManageUserRule;
import com.yjc.system.admin.service.ManageRoleService;
import com.yjc.system.admin.service.ManageUserRuleService;
import com.yjc.system.admin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:36:08
 */
@Service("manageUserRuleService")
public class ManageUserRuleServiceImpl extends ServiceImpl<ManageUserRuleMapper, ManageUserRule> implements ManageUserRuleService {

  /**
   * ${comments}简单分页查询
   * @param manageUserRule ${comments}
   * @return
   */

  @Autowired
  private ManageRoleService manageRoleService;

  @Autowired
  private RedisService redisService;


  @Override
  public IPage<ManageUserRule> getManageUserRulePage(Page<ManageUserRule> page, ManageUserRule manageUserRule){
      return baseMapper.getManageUserRulePage(page,manageUserRule);
  }

    @Override
    public boolean inOrUpdate(UpdateUserDto updateUserDto) {
      try {

          //删除所有原用户角色关联关系
          baseMapper.delete(new UpdateWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId,updateUserDto.getId()).eq(ManageUserRule::getUserNo,updateUserDto.getUserNo()));

          //根据角色id获取角色对象
          List<ManageRole> rolds = new ArrayList<>(manageRoleService.selectBatchIds(updateUserDto.getRoleIds()));
          Optional.ofNullable(rolds).ifPresent(list->{
              list.forEach(role->{
                  ManageUserRule userRule=new ManageUserRule();
                  userRule.setUserId(updateUserDto.getId());
                  userRule.setUserLoginNo(updateUserDto.getUserLoginNo());
                  userRule.setUserNo(updateUserDto.getUserNo());
                  userRule.setUserName(updateUserDto.getUserName());
                  userRule.setRoleId(role.getRoleId());
                  userRule.setRoleName(role.getRoleName());
                  baseMapper.insert(userRule);
              });
          });
          redisService.delUserById(updateUserDto.getId());
      }catch (Exception e){
          return false;
      }

        return true;
    }



}
