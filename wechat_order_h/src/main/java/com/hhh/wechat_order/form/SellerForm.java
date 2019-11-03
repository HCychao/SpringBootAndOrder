package com.hhh.wechat_order.form;

import lombok.Data;

/**
 * 商家表单
 * @author HHH
 * @version 1.0 2019/10/17
 */
@Data
public class SellerForm {

    private String username;
    private String password;
    private String phone;
    private Integer sellerId;
}
