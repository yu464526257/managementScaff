package com.yjc.system.admin;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,SecurityAutoConfiguration.class})
@ComponentScan("com.yidi.system")
@MapperScan({"com.yidi.system.admin.dao","com.yidi.system.commen.common.file.dao","com.yidi.system.commen.dao","com.yidi.system.personnel.mapper"})
public class SystemAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemAdminApplication.class, args);
    }

}
