package com.yjc.system.admin.controller;

import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageMenu;
import com.yjc.system.admin.service.ManageMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-13 14:39:52
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managemenu")
@Api(tags="菜单")
public class ManageMenuController {

  private final ManageMenuService manageMenuService;
  /**
   * Func Ztree树形列表请求方法  全部菜单
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/getManageMenuTree", method = RequestMethod.POST)
  @ApiOperation("树形列表请求方法-全部菜单")
  public ResultEntity getManageMenuTree(){
    return ResultEntity.ok(manageMenuService.getManageMenuTree());
  }

  /**
   * 新增菜单
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
  @ApiOperation("新增菜单")
  public ResultEntity addMenu(@RequestBody ManageMenu manageMenu, HttpServletRequest request){
    return manageMenuService.addManageMenu(manageMenu,request);
  }

  /**
   * 修改菜单
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
  @ApiOperation("修改菜单")
  public ResultEntity updateMenu(@RequestBody ManageMenu manageMenu, HttpServletRequest request){
    return manageMenuService.updateManageMenu(manageMenu,request);
  }


  /**
   * 删除菜单
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
  @ApiOperation("删除菜单")
  public ResultEntity deleteMenu(@RequestParam(value = "menuId",required = false) Long menuId, HttpServletRequest request){
    return manageMenuService.deleteManageMenu(menuId,request);
  }

}
