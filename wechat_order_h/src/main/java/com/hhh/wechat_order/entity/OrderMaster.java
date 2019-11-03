package com.hhh.wechat_order.entity;

import com.hhh.wechat_order.enums.OrderStatusEnum;
import com.hhh.wechat_order.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单的主表
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class OrderMaster {

    //订单的id
    @Id
    private String orderId;

    //买家名字
    private String buyerName;

    //买家的电话
    private String buyerPhone;

    //买家的地址
    private String buyerAddress;

    //买家微信Openid
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态，默认为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态,默认为0,未支付
    //TODO
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

}
