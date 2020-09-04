package com.yjc.system.commen.common.redis;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/5
 * 所属功能
 */

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {



        @Autowired
        private RedisTemplate<String, Object> redisTemplate;

        /**
         * 指定缓存失效时间
         * @param key 键
         * @param time 时间(秒)
         * @return
         */
        public boolean expire(String key, long time) {
            try {
                if (time > 0) {
                    redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
                }
                return true;
            } catch (Exception e) {
                log.error("错误："+Thread.currentThread().getStackTrace()[1].getMethodName(),e);
                return false;
            }
        }
        /**
         * 根据key 获取过期时间
         * @param key 键 不能为null
         * @return 时间(秒) 返回0代表为永久有效
         */
        public long getExpire(String key) {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        }
        /**
         * 判断key是否存在
         * @param key 键
         * @return true 存在 false不存在
         */
        public boolean hasKey(String key) {
            try {
                return redisTemplate.hasKey(key);
            } catch (Exception e) {
                log.error("错误："+Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
                return false;
            }
        }


        public boolean setStrValue(String key,String value){
            try {
                redisTemplate.opsForValue().set(key, value);
                return true;
            }catch (Exception e){
                log.error("错误："+Thread.currentThread().getStackTrace()[1].getMethodName(),e);
                return false;
            }
        }

        public boolean setObjValue(String key,String obj ){
            try {
                redisTemplate.opsForValue().set(key, obj);
                return true;
            }catch (Exception e){
                return false;
            }
        }

        public boolean del(String key){
            try {
                redisTemplate.delete(key);
                return true;
            }catch (Exception e){
                return false;
            }
        }

        public Long incr(String key,Long data){
            try {
                if(data<0){
                    return 0l;
                }
                return redisTemplate.opsForValue().increment(key,data);
            }catch (Exception e){
                return 0l;
            }
        }

        public <T> T getObject(String key,Class<T> c){
            try{
                Gson son=new Gson();
                T t = son.fromJson((String) redisTemplate.opsForValue().get(key),c);
                return  t;
            }catch (Exception e){
                return null;
            }
        }


}
