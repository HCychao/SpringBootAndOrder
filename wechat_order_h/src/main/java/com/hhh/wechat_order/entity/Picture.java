package com.hhh.wechat_order.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 轮播图
 * @author HHH
 * @version 1.0 2019/10/16
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Picture {

    //图片id
    @Id
    @GeneratedValue
    private Integer picId;

    //图片地址
    private String picUrl;

    //图片信息
    private String picMessage;

    //创建时间
    private Date createTime;
}
