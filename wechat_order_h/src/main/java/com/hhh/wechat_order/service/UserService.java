package com.hhh.wechat_order.service;

import com.hhh.wechat_order.entity.User;

/**
 * 用户端
 * @author HHH
 * @version 1.0 2019/10/17
 */
public interface UserService {

    //通过 openid 查询用户信息
    User findByOpenid(String openid);

    //保存
    void save(User user);
}
