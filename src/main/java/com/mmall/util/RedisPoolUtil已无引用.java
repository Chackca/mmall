package com.mmall.util;

import com.mmall.common.RedisPool已无引用;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * Created by Chackca
 */

@Slf4j
public class RedisPoolUtil已无引用 {

    /**
     * 重新设置key的有效期，单位是秒
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key,int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool已无引用.getJedis();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            RedisPool已无引用.returnBrokenResource(jedis);
            return result;
        }
        RedisPool已无引用.returnResource(jedis);
        return result;
    }

    //exTime的单位是秒
    public static String setEx(String key,String value,int exTime){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool已无引用.getJedis();
            result = jedis.setex(key,exTime,value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error",key,value,e);
            RedisPool已无引用.returnBrokenResource(jedis);
            return result;
        }
        RedisPool已无引用.returnResource(jedis);
        return result;
    }

    public static String set(String key,String value){
        Jedis jedis = null;
        String result = null;

        try {
            jedis = RedisPool已无引用.getJedis();
            result = jedis.set(key,value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error",key,value,e);
            RedisPool已无引用.returnBrokenResource(jedis);
            return result;
        }
        RedisPool已无引用.returnResource(jedis);
        return result;
    }

    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool已无引用.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error",key,e);
            RedisPool已无引用.returnBrokenResource(jedis);
            return result;
        }
        RedisPool已无引用.returnResource(jedis);
        return result;
    }

    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool已无引用.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error",key,e);
            RedisPool已无引用.returnBrokenResource(jedis);
            return result;
        }
        RedisPool已无引用.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        /*Jedis jedis = RedisPool已无引用.getJedis();

        RedisShardedPoolUtilBak.set("keyTest","value");

        String value = RedisShardedPoolUtilBak.get("keyTest");

        RedisShardedPoolUtilBak.setEx("keyex","valueex",60*10);

        RedisShardedPoolUtilBak.expire("keyTest",60*20);

        RedisShardedPoolUtilBak.del("keyTest");


        String aaa = RedisShardedPoolUtilBak.get(null);
        System.out.println(aaa);

        System.out.println("end");*/

    }


}