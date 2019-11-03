package com.hhh.wechat_order.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVo<T> {

    //错误码
    private  Integer code;

    //提示信息
    private String msg;

    //返回的具体对象
    private T data;

}
