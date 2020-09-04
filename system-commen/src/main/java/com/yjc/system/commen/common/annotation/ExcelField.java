package com.yjc.system.commen.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 自定义导出Excel数据注解
* @author 于峻成
* @description
**/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelField
{
    /**
     * 导出到Excel中的名字.
     */
    String name();

    /**
     * 提示信息
     */
    String prompt() default "";

    /**
     * 设置只能选择不能输入的列内容.
     */
    String[] combo() default {};

    /**
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
    boolean isExport() default true;
}