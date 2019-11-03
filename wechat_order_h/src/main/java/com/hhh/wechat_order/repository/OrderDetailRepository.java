package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail , String> {

    /**
     * 通过 orderId 查询所有的订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
