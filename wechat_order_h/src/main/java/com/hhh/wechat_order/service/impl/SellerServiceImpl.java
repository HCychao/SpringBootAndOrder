package com.hhh.wechat_order.service.impl;

import com.hhh.wechat_order.entity.SellerInfo;
import com.hhh.wechat_order.repository.SellerInfoRepository;
import com.hhh.wechat_order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}
