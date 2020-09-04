package com.yjc.system.admin.controller;


import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageUserModePower;
import com.yjc.system.admin.service.ManageUserModePowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户业务数据权限关联表
 *
 * @author yjc
 * @date 2020-07-05 18:58:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("/manageusermodepower")
@Api(tags="用户业务数据权限")
public class ManageUserModePowerController {

  private final ManageUserModePowerService manageUserModePowerService;


  @GetMapping("/getManageModeUserList")
  @ApiOperation(value = "查询用户相关业务")
  ResultEntity getManageModeUserList(String userId) {
    return ResultEntity.ok(manageUserModePowerService.getManageModeUserList(userId));
  }

  @PostMapping("/delManageModeUserById")
  @ApiOperation(value = "删除单条用户相关业务")
  ResultEntity delManageModeUserById(int modeCode, int powerCode, String userId) {
    return ResultEntity.ok(manageUserModePowerService.delManageModeUser(modeCode, powerCode, userId));
  }

  @PostMapping("/delAllManageModeUser")
  @ApiOperation(value = "删除用户相关所有业务")
  ResultEntity delAllManageModeUser(String userId) {
    return ResultEntity.ok(manageUserModePowerService.delAllManageModeUser(userId));
  }

  @PostMapping("/editManageModeUser")
  @ApiOperation(value = "修改用户相关业务")
  ResultEntity editManageModeAllUser(String userId, List<ManageUserModePower> list) {
    return ResultEntity.ok(manageUserModePowerService.updateUserMode(userId, list));
  }

}
