package com.yjc.system.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.UserLoginLoad;
import com.yjc.system.admin.service.UserLoginLoadService;
import com.yjc.system.commen.common.utils.UuidUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 用户登录记录表


 *
 * @author yjc
 * @date 2020-06-29 13:45:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userloginload")
@Api(tags="用户登录记录")
public class UserLoginLoadController {

  private final UserLoginLoadService userLoginLoadService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param userLoginLoad 用户登录记录表


   * @return
   */
  @GetMapping("/page")
  public ResultEntity<IPage<UserLoginLoad>> getUserLoginLoadPage(Page<UserLoginLoad> page, UserLoginLoad userLoginLoad) {
    return  ResultEntity.ok(userLoginLoadService.getUserLoginLoadPage(page,userLoginLoad));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return ResultEntity
   */
  @GetMapping("/{id}")
  public ResultEntity<UserLoginLoad> getById(@PathVariable("id") String id){
    return ResultEntity.ok(userLoginLoadService.selectById(id));
  }

  /**
   * 新增记录
   * @param
   * @return ResultEntity
   */
  @PostMapping("save")
  @ResponseBody
  public void save(@RequestBody UserLoginLoad userLoginLoad){
    userLoginLoad.setId(UuidUtil.getUuidFor32());
    userLoginLoad.setLoginTime(LocalDateTime.now());
    userLoginLoadService.insert(userLoginLoad);
  }



}
