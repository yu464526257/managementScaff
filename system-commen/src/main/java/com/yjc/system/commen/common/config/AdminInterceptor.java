package com.yjc.system.commen.common.config;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/22
 * 所属功能  拦截器
 */

import com.yjc.system.commen.common.enums.CommonConstants;
import com.yjc.system.commen.common.utils.IpBadCheck;
import com.yjc.system.commen.dto.base.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private  IpBadCheck ipBadCheck;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ip=ipBadCheck.getIpAddress(request);
        ResultEntity resultEntity=ipBadCheck.badCheck(ip);
        if(resultEntity.getCode()!=CommonConstants.SUCCESS){
            request.setAttribute("data",resultEntity);
            return false;
        }
        return true;
    }

}
