package com.yjc.system.admin.controller;


import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageRoleMenu;
import com.yjc.system.admin.service.ManageRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色跟老得岗对应
 *
 * @author yjc
 * @date 2020-07-10 15:50:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managerolemenu")
@Api(tags="角色岗位")
public class ManageRoleMenuController {

  private final ManageRoleMenuService manageRoleMenuService;

  /**
   * 新增角色关联菜单表
   * @param manageRoleMenu
   * @return ResultEntity
   */
  @ResponseBody
  @PostMapping("/save")
  @ApiOperation(value = "新增角色对应菜单关联表")
  public ResultEntity save(@RequestBody ManageRoleMenu manageRoleMenu){
    return manageRoleMenuService.save(manageRoleMenu);
  }


  /**
   * 修改角色关联菜单表
   * @param manageRoleMenu
   * @return ResultEntity
   */
  @ResponseBody
  @PostMapping("/modify")
  @ApiOperation(value = "修改角色对应菜单关联表")
  public ResultEntity modify(@RequestBody ManageRoleMenu manageRoleMenu){
    return manageRoleMenuService.modify(manageRoleMenu);
  }

  /**
   * 根据角色编号和菜单编号删除角色关联菜单表
   * @param roleId
   * @param menuId
   * @return ResultEntity
   */
  @ResponseBody
  @PostMapping("/remove")
  @ApiOperation(value = "根据角色编号和菜单编号删除角色关联菜单表")
  public ResultEntity remove(@RequestParam(value = "roleId",required = false) String roleId,@RequestParam(value = "menuId",required = false) Long menuId){
    return manageRoleMenuService.remove(roleId,menuId);
  }



  /**
   * 根据角色编号集合查询菜单信息
   * @param roles
   * @return ResultEntity
   */
  @ResponseBody
  @PostMapping("/getMenuDtoByRoles")
  @ApiOperation(value = "根据角色编号集合查询菜单信息")
  public ResultEntity getMenuDtoByRoles(@RequestBody List<String> roles){
    return manageRoleMenuService.getMenuDtoByRoles(roles);
  }


  /**
   * Func Ztree树形列表请求方法
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getList", method = RequestMethod.POST)
  @ApiOperation("树形列表请求方法")
  public ResultEntity getList(@RequestBody List<String> roles){
    return ResultEntity.ok(manageRoleMenuService.getList(roles));
  }


  /**
   * 根据单个角色id查询菜单列表
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getMenuDtoByRoleId", method = RequestMethod.POST)
  @ApiOperation("树形列表请求方法")
  public ResultEntity getMenuDtoByRoleId(@RequestParam(value = "roleId",required = false) String roleId){
    return ResultEntity.ok(manageRoleMenuService.getMenuDtoByRoleId(roleId));
  }


}
