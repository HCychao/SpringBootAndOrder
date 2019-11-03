package com.hhh.wechat_order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 买家表单
 * @author HHH
 * @version 1.0 2019/10/17
 */
@Data
public class UserForm {

    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String username;

    //买家手机号
    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家openid
    @NotEmpty(message = "openid必填")
    private String openid;

    private String usertable;

    private String usernumber;
}
