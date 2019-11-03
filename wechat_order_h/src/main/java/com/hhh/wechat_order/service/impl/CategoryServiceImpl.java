package com.hhh.wechat_order.service.impl;

import com.hhh.wechat_order.entity.ProductCategory;
import com.hhh.wechat_order.entity.ProductInfo;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.repository.ProductCategoryRepository;
import com.hhh.wechat_order.repository.ProductInfoRepository;
import com.hhh.wechat_order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * 商品类目的Service层实现类
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    //根据类目的id查找类目的信息
    @Override
    public Optional<ProductCategory> findById(Integer categoryId) {
        return repository.findById(categoryId);
    }

    //查找所有的类目信息
    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    //根据类目编号列表的值，查找出类目信息（多条）
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    //保存类目信息
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    //删除类目
    @Override
    public void delete(Integer categoryId) {
        Optional<ProductCategory> category = repository.findById(categoryId);
        List<ProductInfo> productInfoList = productInfoRepository.findByCategoryType(category.get().getCategoryType());
        if( !productInfoList.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_IN_CATEGORY_ERROR);
        }
        repository.delete(category.get());
    }

}
