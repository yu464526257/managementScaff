package com.yjc.system.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;

import com.yjc.system.admin.dto.LoginUserDto;
import com.yjc.system.admin.dto.NewAddUserDto;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.dto.UserChangePasswordDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.service.ManageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Map;


/**
 * 用户表
 *
 * @author yjc
 * @date 2020-07-05 17:12:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/manageuser")
@Api(tags="用户")
public class ManageUserController {

  private final ManageUserService manageUserService;

  @PostMapping("/loginOld")
  @ResponseBody
  @ApiOperation("用户登录接口（第一版）")
  public ResultEntity loginOld(@RequestBody String data){
    Gson gson = new Gson();
    Map<String,String> user=gson.fromJson(data,Map.class);
    return manageUserService.loginOld(user);
  }


  @PostMapping("/login")
  @ResponseBody
  @ApiOperation("用户登录接口（第二版）")
  public ResultEntity login(@RequestBody @Validated LoginUserDto user, HttpServletRequest request, HttpServletResponse response){
    return manageUserService.login(user,request,response);
  }



  @PostMapping("/addNewUser")
  @ResponseBody
  @ApiOperation("新增用户")
  public ResultEntity addNewUser(@RequestBody @Validated NewAddUserDto user){
      return manageUserService.addNewUser(user);
  }


  @PostMapping("/getUserById")
  @ResponseBody
  @ApiOperation("根据用户id获取用户")
  public  ResultEntity getUserById(@RequestBody @NotNull String id){
      return ResultEntity.ok(manageUserService.getUserById(id));
  }

  @PostMapping("/getUserInfo")
  @ResponseBody
  @ApiOperation("获取登陆用户")
  public  ResultEntity getUserInfo(HttpServletRequest request){
    return ResultEntity.ok(manageUserService.getUserInfo(request));
  }


  @PostMapping("/updateUser")
  @ResponseBody
  @ApiOperation("修改用户")
  public ResultEntity updateUser(@RequestBody @Validated UpdateUserDto user){
      return manageUserService.updateUser(user);
  }

  @PostMapping("/updateUserPassword")
  @ResponseBody
  @ApiOperation("修改用户密码")
  public ResultEntity updateUserPassword(@RequestBody @Validated UserChangePasswordDto user){
      return manageUserService.updateUserPassword(user);
  }

//  @PostMapping("/getManageUser")
//  @ResponseBody
//  @ApiOperation("查询全部用户")
//  public ResultEntity getManageUser(Page page, @RequestBody ManageUser manageUser){
//    return manageUserService.getManageUser(page,manageUser);
//  }

  @PostMapping("/getManageUser")
  @ResponseBody
  @ApiOperation("查询全部用户")
  public ResultEntity getManageUser(Page page){
    return manageUserService.getManageUser(page);
  }




}
