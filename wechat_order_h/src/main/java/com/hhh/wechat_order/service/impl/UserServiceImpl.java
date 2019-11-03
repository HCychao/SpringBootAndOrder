package com.hhh.wechat_order.service.impl;

import com.hhh.wechat_order.entity.User;
import com.hhh.wechat_order.repository.UserRepository;
import com.hhh.wechat_order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HHH
 * @version 1.0 2019/10/17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }
}
