package com.hhh.wechat_order.service.impl;

import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.service.BuyerService;
import com.hhh.wechat_order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    //判断是否属于自己的订单方法
    private OrderDTO checkOrderOwner(String openid , String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        //判断订单是否为空
        if(orderDTO == null){
            return null;
        }
        //判断是否属于自己的订单
        if( !orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致 , openid={} , orderDTO={}" ,openid , orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid , orderId);
    }

    @Override
    public List<OrderDTO> findOrderList(String openid, Integer status) {
        List<OrderDTO> list = new ArrayList<>();
        list.clear();

        List<OrderDTO> listStats = orderService.findListStats(openid , status);
        listStats.forEach((orderDTO) -> {
            String orderId = orderDTO.getOrderId();
            OrderDTO one = orderService.findOne(orderId);
            list.add(one);
        });
        return list;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO == null){
            log.error("【取消订单】查不到改订单 , orderId={}" , orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

}
