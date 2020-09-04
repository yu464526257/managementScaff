package com.yjc.system.commen.common.config;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/22
 * 所属功能
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public AdminInterceptor getAdminInterceptor(){
        return  new AdminInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        //
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(getAdminInterceptor());
        registration.addPathPatterns("/**");                      //
        registration.excludePathPatterns(
                "swagger-ui.html",//
                "/**/*.html",            //
                "/**/*.js",              //
                "/**/*.css",             //
                "/**/*.woff",
                "/**/*.ttf"
        );

    }
}
