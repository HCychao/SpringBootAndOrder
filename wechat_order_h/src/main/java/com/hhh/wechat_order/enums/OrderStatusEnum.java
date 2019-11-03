package com.hhh.wechat_order.enums;

import lombok.Getter;

/**
 * 订单状态
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0,"新订单未支付") ,
    NEW_PAYED(1 , "新订单已支付" ) ,
    CANCEL(2,"订单取消") ,
    FINSHED(3,"订单完成"),
    COMMENT(4 , "已评价") ,
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
