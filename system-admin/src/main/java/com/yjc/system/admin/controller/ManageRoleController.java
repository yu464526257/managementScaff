package com.yjc.system.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yjc.system.admin.dto.ManageRoleDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageRole;
import com.yjc.system.admin.service.ManageRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 角色表Controller
 * @author yjc
 * @date 2020-07-10 15:46:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managerole")
@Api(tags="角色")
public class ManageRoleController {

  @Autowired
  private ManageRoleService manageRoleService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param manageRoleDto
   * @return
   */
  @PostMapping("/page")
  @ResponseBody
  @ApiOperation(value = "简单分页查询")
  public ResultEntity<IPage<ManageRoleDto>> getStockPutInPage(Page<ManageRoleDto> page, @RequestBody ManageRoleDto manageRoleDto) {
    return  ResultEntity.ok(manageRoleService.getManageRolePage(page,manageRoleDto));
  }

  /**
   * 查询全部
   * @return
   */
  @PostMapping("/getManageRoleList")
  @ResponseBody
  @ApiOperation(value = "查询全部")
  public ResultEntity getManageRoleList() {
    return  manageRoleService.getManageRoleList();
  }

  /**
   * 根绝角色id查询角色信息
   * @param roleId
   * @return
   */
  @PostMapping("/getManageRoleByRoleId")
  @ResponseBody
  @ApiOperation(value = "根据角色id查询角色信息")
  public ResultEntity getManageRoleByRoleId(@RequestParam(value = "roleId",required = false) String roleId) {
    return  manageRoleService.getManageRoleByRoleId(roleId);
  }


  /**
   * 新增角色
   * @param manageRole  角色表对象
   * @return
   */
  @PostMapping("/save")
  @ResponseBody
  @ApiOperation(value = "新增角色")
  public ResultEntity save(@RequestBody ManageRole manageRole, HttpServletRequest request){
    return manageRoleService.save(manageRole,request);
  }

  /**
   * 修改角色
   * @param manageRole  角色表对象
   * @return
   */
  @PostMapping("/update")
  @ResponseBody
  @ApiOperation(value = "修改角色")
  public ResultEntity update(@RequestBody ManageRole manageRole,HttpServletRequest request){
    return manageRoleService.update(manageRole,request);
  }

  /**
   * 删除角色
   * @param roleId  角色id
   * @return
   */
  @PostMapping("/delete")
  @ResponseBody
  @ApiOperation(value = "删除角色")
  public ResultEntity delete(@RequestParam(value = "roleId",required = false) String roleId,HttpServletRequest request){
    return manageRoleService.delete(roleId,request);
  }

}
