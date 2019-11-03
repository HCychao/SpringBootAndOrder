package com.hhh.wechat_order.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 评论表
 * @author HHH
 * @version 1.0 2019/10/16
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Comment {

    //评论表id
    @Id
    @GeneratedValue
    private Integer commentId;

    //评论者的名字
    private String commentName;

    //评论者的openid
    private String openid;

    //评论内容
    private String commentContent;

    //评论者的头像
    private String avatarUrl;

    //创建时间
    private Date createTime;
}
