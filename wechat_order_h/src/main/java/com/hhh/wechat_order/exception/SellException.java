package com.hhh.wechat_order.exception;

import com.hhh.wechat_order.enums.ResultEnum;

/**
 * 异常错误
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code , String message) {
        super(message);
        this.code = code;
    }

}
