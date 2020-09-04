package com.yjc.system.commen.service;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/24
 * 所属功能
 */

import com.yjc.system.commen.dto.MyProcessInstance;
import com.yjc.system.commen.dto.MyProcessInstance;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.util.List;

public interface ActService {


    /**
     *     启动流程-固定审批人流程
     */
    MyProcessInstance startProcessInstanceOfFixed(String key, String nextUser);

    /**
     *     //启动流程-多候选人审批人流程
     * @param key
     * @param nextUsers
     * @return
     */
    MyProcessInstance startProcessInstanceOfArray(String key,List<String> nextUsers);
    /**
     *     根据当前人id获未签收任务
     */
    List<MyProcessInstance> findPersonalNotCheckTask(String userId,String processInstanceKey);
    /**
     *     根据当前人获取已签收任务
     */
    List<MyProcessInstance> findPersonalCheckTask(String userId,String processInstanceKey);

    /**
     * 执行任务
     * @param taskId
     */
    void completePersonalTask(String taskId);

    /**
     * 删除流程
     * @param processInstanceId
     * @param remark
     */
    void deleteProcessInstance(String processInstanceId,String remark);

    /**
     * /删除流程
     * @param DeploymentId
     */
    void deleteDeployment(String DeploymentId);

    /**
     * 给任务添加执行人
     * @param taskId
     * @param candidateUser
     */
    void addCandidateUser(String taskId,String candidateUser);

    /**
     * 给任务添加执行人组
     * @param taskId
     * @param candidateGroup
     */
    void addCandidateGroup(String taskId,String candidateGroup);

    /**
     * 领取任务
     * @param taskId
     * @param userId
     */
    void claim(String taskId, String userId);

    /**
     * 放弃签收
     * @param taskId
     */
    void unclaim(String taskId);

    /**
     * 委托领取（转签）
     * @param taskId
     * @param userId
     */
    void delegateTask(String taskId, String userId);
}
