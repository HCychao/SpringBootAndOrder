package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息Dao接口
 * @author HHH
 * @version 1.0 2019/10/17
 */
public interface UserRepository extends JpaRepository<User , String> {

    /**
     * 通过 openid 查询用户信息
     * @param openid
     * @return
     */
    User findByOpenid(String openid);
}
