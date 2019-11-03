package com.hhh.wechat_order.service;

import com.hhh.wechat_order.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单的Service层
 */
public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询订单列表(指定买家的订单)
    Page<OrderDTO> findList(String buyerOpenid , Pageable pageable);

    //查询订单列表(所有买家的订单)
    Page<OrderDTO> findList(Pageable pageable);

    //查询订单列表(所有买家的订单)根据创建时间排序(降序)
    Page<OrderDTO> findOrderMastersByCreateTimeDesc(Pageable pageable);

    //查询不同状态的订单列表
    List<OrderDTO> findListStats(String buyerOpenid , Integer orderStatus);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);

}
