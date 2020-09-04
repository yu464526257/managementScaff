package com.yjc.system.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.dto.ManageRoleDto;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageRole;
import com.yjc.system.admin.entity.ManageUserRule;
import com.yjc.system.admin.service.ManageUserRuleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:36:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/manageuserrule")
public class ManageUserRuleController {

  private final ManageUserRuleService manageUserRuleService;


  /**
   * 简单分页查询
   * @param page 分页对象
   * @param manageRoleDto
   * @return
   */
  @PostMapping("/page")
  @ResponseBody
  @ApiOperation(value = "简单分页查询")
  public ResultEntity<IPage<ManageUserRule>> getStockPutInPage(Page<ManageUserRule> page, @RequestBody ManageUserRule manageUserRule) {
    return  ResultEntity.ok(manageUserRuleService.getManageUserRulePage(page,manageUserRule));
  }



  /**
   * 根绝角色id查询角色信息
   * @param
   * @return
   */
  @PostMapping("/getManageRoleByRoleId")
  @ResponseBody
  @ApiOperation(value = "根绝角色id查询角色信息")
  public ResultEntity getManageRoleByRoleId(@RequestBody ManageUserRule manageUserRule) {
    return  ResultEntity.ok(manageUserRuleService.selectList(new QueryWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId,manageUserRule.getUserId())));
  }


  /**
   * 新增角色
   * @param   新增/修改角色
   * @return
   */
  @PostMapping("/save")
  @ResponseBody
  @ApiOperation(value = "新增/修改角色")
  public ResultEntity inOrUpdate(@RequestBody UpdateUserDto updateUserDto){
    return ResultEntity.ok(manageUserRuleService.inOrUpdate(updateUserDto));
  }






}
