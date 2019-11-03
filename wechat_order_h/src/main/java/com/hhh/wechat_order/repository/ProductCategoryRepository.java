package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 商品类目的Dao层
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory , Integer> {

    /**
     *通过 类目编号 查询类目
     * @param categoryTypeList 类目编号的列表
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
