package com.yjc.system.admin.controller;

import com.yjc.system.admin.service.ManageUserDeptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户部门关联表
 *
 * @author yjc
 * @date 2020-07-10 15:12:00
 */
@RestController
@AllArgsConstructor
@RequestMapping("/manageuserdept")
public class ManageUserDeptController {

  private final ManageUserDeptService manageUserDeptService;


}
