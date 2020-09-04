package com.yjc.system.commen.common.utils;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/22
 * 所属功能  简易防Ip攻击业务类
 */

import com.yjc.system.commen.common.enums.CommonConstants;
import com.yjc.system.commen.common.redis.RedisUtil;
import com.yjc.system.commen.dto.base.ResultEntity;
import com.yjc.system.commen.common.enums.CommonConstants;
import com.yjc.system.commen.common.redis.RedisUtil;
import com.yjc.system.commen.dto.base.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class IpBadCheck {

    @Autowired
    private RedisUtil redisUtil;

    //参数 最大速率
    private static Long maxRate=5l;
    //参数 限制时长
    private static Long limitTime=1000L*60l*5l;
    //参数 速率时效
    private static long maxTimeLimit=1000L;

    //方法一：根据ip去redis 做incr 操作。得到当前值。判断是否超长
    //方法一拆解： 1 ip 做incr当返回值为1时做失效时间 2，判断最大效率 超过最大效率拦截器返回 ，且重新设置失效时间 3.当数值没有超过失效时间然会正常
    public ResultEntity badCheck(String ip){
        if(StringUtils.isEmpty(ip)){
            return ResultEntity.failed("ip未获取到，无法访问");
        }
        ip=ip+DateUtil.dateToStr(new Date(),5);
        long rate=0;
        rate=redisUtil.incr(ip,CommonConstants.ipData);
        if(rate==1){
            redisUtil.expire(ip,maxTimeLimit);
            return ResultEntity.ok();
        }
        if(rate>maxRate){
            redisUtil.expire(ip,limitTime);
            return ResultEntity.failed("您操作做过快，请休息一下吧。");
        }
        return ResultEntity.ok();
    }

    /** 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public  String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}
