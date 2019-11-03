package com.hhh.wechat_order.service;

import com.hhh.wechat_order.dto.CartDTO;
import com.hhh.wechat_order.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 *商品Service层的接口
 */
public interface ProductService {

    //通过商品id查询商品
    Optional<ProductInfo> findById(String productId);

    //查询所有在架商品列表
    List<ProductInfo> findUpAll();

    //查询所有商品(分页)
    Page<ProductInfo> findAll(Pageable pageable);

    //通过类目编号查询商品列表
    List<ProductInfo> findByCategoryType(Integer categoryType);

    //保存商品
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架商品
    ProductInfo onSale(String productId);

    //下架商品
    ProductInfo offSale(String productId);

    //删除商品
    void delete(String productId);

}
