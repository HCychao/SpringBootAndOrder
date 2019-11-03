package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商家信息Dao接口
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo , Integer> {

    /**
     * 通过 phone 查询商家信息
     * @param phone
     * @return
     */
    SellerInfo findByPhone(String phone);

    /**
     * 通过 id 查询商家信息
     * @param sellerId
     * @return
     */
    SellerInfo findBySellerId(Integer sellerId);
}
