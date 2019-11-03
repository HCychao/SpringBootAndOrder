package com.hhh.wechat_order.constant;

/**
 * redis 的常量
 */
public interface RedisConstant {

    //redis的前缀
    String TOKEN_PREFIX = "token_%s";

    //redis的过期时间
    Integer EXPIRE = 7200;
}
