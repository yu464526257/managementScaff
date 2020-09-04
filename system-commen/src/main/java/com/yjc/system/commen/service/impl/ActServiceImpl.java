package com.yjc.system.commen.service.impl;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/24
 * 所属功能
 */

import com.yjc.system.commen.common.utils.StringUtils;
import com.yjc.system.commen.dto.MyProcessInstance;
import com.yjc.system.commen.service.ActService;
import com.yjc.system.commen.common.utils.StringUtils;
import com.yjc.system.commen.dto.MyProcessInstance;
import com.yjc.system.commen.service.ActService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ActServiceImpl implements ActService {


    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;



    @Override
    public MyProcessInstance startProcessInstanceOfFixed(String key, String nextUser) {
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(key);
        Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().singleResult();
        this.addCandidateUser(task.getId(),nextUser);
        MyProcessInstance myProcessInstance=new MyProcessInstance();
        myProcessInstance.setProcessId(processInstance.getId());
        myProcessInstance.setTaskId(task.getId());
        return myProcessInstance;
    }

    @Override
    public MyProcessInstance startProcessInstanceOfArray(String key, List<String> nextUsers) {
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(key);
        Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().singleResult();
        this.addCandidateGroup(task.getId(),nextUsers.toString());
        MyProcessInstance myProcessInstance=new MyProcessInstance();
        myProcessInstance.setProcessId(processInstance.getId());
        myProcessInstance.setTaskId(task.getId());
        return myProcessInstance;
    }

    @Override
    public List<MyProcessInstance> findPersonalNotCheckTask(String userId,String processInstanceKey) {
        TaskQuery query = this.taskService.createTaskQuery();
        //指定个人任务查询，指定办理人
        query.taskCandidateUser(userId);
        if(StringUtils.isNotEmpty(processInstanceKey)){
            query.processDefinitionKeyLike(processInstanceKey);
        }
        List<MyProcessInstance> mylist=new ArrayList<>();
        List<Task> list = query.list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                MyProcessInstance myProcessInstance=new MyProcessInstance();
                myProcessInstance.setTaskId(task.getId());
                myProcessInstance.setProcessId(task.getProcessInstanceId());
                mylist.add(myProcessInstance);
            }
        }
        return mylist;
    }

    @Override
    public List<MyProcessInstance> findPersonalCheckTask(String userId,String processInstanceKey) {
        TaskQuery query = this.taskService.createTaskQuery();
        //指定个人任务查询，指定办理人
        query.taskAssignee(userId);
        if(StringUtils.isNotEmpty(processInstanceKey)){
            query.processDefinitionKeyLike(processInstanceKey);
        }
        List<MyProcessInstance> mylist=new ArrayList<>();
        List<Task> list = query.list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                MyProcessInstance myProcessInstance=new MyProcessInstance();
                myProcessInstance.setTaskId(task.getId());
                myProcessInstance.setProcessId(task.getProcessInstanceId());
                mylist.add(myProcessInstance);
            }
        }
        return mylist;
    }


    @Override
    public void completePersonalTask(String taskId) {
        processEngine.getTaskService().complete(taskId);
    }

    @Override
    public void deleteProcessInstance(String processInstanceId, String remark) {
        runtimeService.deleteProcessInstance(processInstanceId,remark);//删除流程
    }

    @Override
    public void deleteDeployment(String DeploymentId) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment(DeploymentId);
    }


    /**
     * 某个task添加候选人
     * @param taskId
     * @param candidateUser
     */
    public void addCandidateUser(String taskId,String candidateUser) {
        taskService.addCandidateUser(taskId, candidateUser);
    }

    /**
     * 某个task添加候选人组
     * @param taskId
     * @param candidateGroup
     */
    public void addCandidateGroup(String taskId,String candidateGroup) {
        taskService.addCandidateGroup(taskId, candidateGroup);
    }
    /**
     * 任务领取（签收任务）
     * @param taskId
     * @param userId
     */
    public void claim(String taskId, String userId) {
        this.taskService.claim(taskId, userId);
    }

    /**
     * 放弃签收
     * @param taskId
     */
    public void unclaim(String taskId) {
        this.taskService.unclaim(taskId);
    }
    /**
     * 委托领取（转签）
     * @param taskId
     * @param userId
     */
    public void delegateTask(String taskId, String userId) {
        this.taskService.delegateTask(taskId, userId);
    }
}
