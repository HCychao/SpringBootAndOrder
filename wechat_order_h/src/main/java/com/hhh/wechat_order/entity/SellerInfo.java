package com.hhh.wechat_order.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商家信息表
 * @author HHH
 * @version 1.0 2019/10/16
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class SellerInfo {

    //商家用户id
    @Id
    @GeneratedValue
    private Integer sellerId;

    //商家用户名称
    private String username;

    //商家登录密码
    private String password;

    //商家手机
    private String phone;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
