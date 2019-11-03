package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster , String> {

    /**
     * 通过 openid 查询所有的订单
     * @param buyerOpenid
     * @param pageable
     * @return 分页对象
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid , Pageable pageable);

    /**
     * 通过openid 和 orderStatus 查询订单
     * @param buyerOpenid
     * @param orderStatus
     * @return
     */
    List<OrderMaster> findByBuyerOpenidAndOrderStatus(String buyerOpenid , Integer orderStatus);

    @Query("select o from OrderMaster o ORDER BY o.createTime DESC ")
    Page<OrderMaster> findOrderMastersByCreateTimeDesc(Pageable pageable);
}
