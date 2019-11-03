package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 评论的Dao接口
 * @author HHH
 * @version 1.0 2019/10/17
 */
public interface CommentRepository extends JpaRepository<Comment , Integer> {

    /**
     * 通过openid查询所有的评论
     * @param openid
     * @return
     */
    List<Comment> findAllByOpenid(String openid);
}
