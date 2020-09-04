package com.yjc.system.admin.controller;


import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageMode;
import com.yjc.system.admin.service.ManageModeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 业务表
 *
 * @author yjc
 * @date 2020-07-13 15:51:36
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managemode")
@Api(tags="业务")
public class ManageModeController {

  private final ManageModeService manageModeService;

  @GetMapping("/getMenuList")
  @ApiOperation(value = "获取业务关联菜单列表")
  ResultEntity getMenuList(String menuId) {
    return manageModeService.getMenuList(menuId);
  }

  @GetMapping("/getModeList")
  @ApiOperation(value = "获取业务列表")
  ResultEntity getModeList() {
    return manageModeService.getModeList();
  }

  @PostMapping("/insertManageMode")
  @ApiOperation(value = "新增业务")
  ResultEntity addManageMode(String name, int code) {
    return manageModeService.addManageMode(name, code);
  }

  @PostMapping("/delManageMode")
  @ApiOperation(value = "删除业务")
  ResultEntity delManageMode(String id) {
    return manageModeService.delManageMode(id);
  }

  @PostMapping("/editManageMode")
  @ApiOperation(value = "修改业务")
  ResultEntity editManageMode(ManageMode manageMode) {
    return manageModeService.editManageMode(manageMode);
  }

}
