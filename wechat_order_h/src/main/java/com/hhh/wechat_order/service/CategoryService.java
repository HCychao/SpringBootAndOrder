package com.hhh.wechat_order.service;

import com.hhh.wechat_order.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目的Service层接口
 */

public interface CategoryService {

    //根据类目的id查找类目的信息
    Optional<ProductCategory> findById(Integer categoryId);

    //查找所有的类目信息
    List<ProductCategory> findAll();

    //根据类目编号列表的值，查找出类目信息（多条）
   List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    //保存类目信息
    ProductCategory save(ProductCategory productCategory);

    //删除类目
    void delete(Integer categoryId);

}
