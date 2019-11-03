package com.hhh.wechat_order.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 就餐用户信息
 * @author HHH
 * @version 1.0 2019/10/16
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class User {

    //用户id
    @Id
    @GeneratedValue
    private Integer userId;

    //用户名称
    private String userName;

    //用户手机号
    private String userPhone;

    //用户openid
    private String openid;

    //用户就餐的桌号
    private String userTable;

    //用户就餐人数
    private String userNumber;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
