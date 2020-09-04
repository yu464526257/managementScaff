package com.yjc.system.admin.controller;


import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.SysCode;
import com.yjc.system.admin.service.SysCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 字典表
 *
 * @author yjc
 * @date 2020-07-01 14:05:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/syscode")
@Api(tags="字典表")
public class SysCodeController {

  private final SysCodeService sysCodeService;

  @PostMapping("/CodeList")
  @ResponseBody
  @ApiOperation(value = "根据类型获取code/Name")
  public ResultEntity codeList(@RequestBody SysCode sysCode){
      return sysCodeService.codeListByType(sysCode );
  }

//  @PostMapping("/departListByName")
//  @ResponseBody
//  @ApiOperation(value = "获取当前部门的用户信息")
//  public ResultEntity codeList(@RequestBody SysCode sysCode){
//    return sysCodeService.codeListByType(sysCode );
//  }

}
