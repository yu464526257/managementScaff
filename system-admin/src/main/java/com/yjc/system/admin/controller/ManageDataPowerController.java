package com.yjc.system.admin.controller;

import com.yjc.system.admin.service.ManageDataPowerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 数据权限表
 *
 * @author yjc
 * @date 2020-07-13 17:21:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managedatapower")
public class ManageDataPowerController {

  private final ManageDataPowerService manageDataPowerService;


}
