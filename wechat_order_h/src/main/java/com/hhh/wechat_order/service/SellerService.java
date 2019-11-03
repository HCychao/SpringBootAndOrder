package com.hhh.wechat_order.service;

import com.hhh.wechat_order.entity.SellerInfo;

public interface SellerService {

    //通过phone 查询卖家端信息
    SellerInfo findSellerInfoByPhone(String phone);
}
