package com.yjc.system.commen.dto;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/30
 * 所属功能
 */

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
public class MyProcessInstance extends Model<MyProcessInstance> {

    private String processId;


    private String taskId;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
