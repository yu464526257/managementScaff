package com.yjc.system.commen.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yjc.system.commen.common.enums.WhetherEnum;
import com.yjc.system.commen.common.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 默认填充时间，@TableField
 * @author yjc
 * @date 2019/6/21 15:20
 * @Description TODO
 **/
@Component
@Slf4j
public class BaseMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("creationTime",LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        this.setFieldValByName("creationUserId","null",metaObject);
        this.setFieldValByName("updateUserId","null",metaObject);
        this.setFieldValByName("isValid",WhetherEnum.NO.getCode(),metaObject);
        this.setFieldValByName("cjSj",LocalDateTime.now(),metaObject);
        this.setFieldValByName("xgSj",LocalDateTime.now(),metaObject);
        this.setFieldValByName("cjRy","null",metaObject);
        this.setFieldValByName("xgRy","null",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateUserId","null",metaObject);
        this.setFieldValByName("xgSj",LocalDateTime.now(),metaObject);
        this.setFieldValByName("xgRy","null",metaObject);


    }
}
