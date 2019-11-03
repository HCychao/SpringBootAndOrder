package com.hhh.wechat_order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hhh.wechat_order.entity.OrderDetail;
import com.hhh.wechat_order.enums.OrderStatusEnum;
import com.hhh.wechat_order.enums.PayStatusEnum;
import com.hhh.wechat_order.utils.EnumUtil;
import com.hhh.wechat_order.utils.serializer.Date2LongSerializer;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单传输对象类
 */
@Data
public class OrderDTO {

    //订单的id
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
    private Integer orderStatus;

    //支付状态,默认为0,未支付
    private Integer payStatus;

    private String orderStatusStr;

    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //修改时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //订单详情列表
    List<OrderDetail> orderDetailList;

    /**
     * 获取订单状态的枚举类
     * @return
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus , OrderStatusEnum.class);
    }

    /**
     * 获取支付状态的枚举类
     * @return
     */
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus , PayStatusEnum.class);
    }

    @JsonIgnore
    public String getOrderStatusStr(Integer orderStatus2) {
        return EnumUtil.getByCode(orderStatus2, OrderStatusEnum.class).getMessage();
    }
}
