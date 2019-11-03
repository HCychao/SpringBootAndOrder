package com.hhh.wechat_order.service.impl;

import com.hhh.wechat_order.entity.ProductInfo;
import com.hhh.wechat_order.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findById() {
        Optional<ProductInfo> productInfo = productService.findById("123456");
        System.out.println(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0 , productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0 , 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("456789");
        productInfo.setProductName("白粥");
        productInfo.setProductPrice(new BigDecimal(1.2));
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setProductIcon("http://oooo.jpg");
        productInfo.setProductDescription("白粥很绵");
        productInfo.setProductStock(50);
        productService.save(productInfo);
    }

    @Test
    public void onSaleTest(){
        ProductInfo result = productService.onSale("111");
        Assert.assertEquals(ProductStatusEnum.UP , result.getProductStatusEnum());
    }

    @Test
    public void offSaleTest(){
        ProductInfo result = productService.offSale("111");
        Assert.assertEquals(ProductStatusEnum.DOWN , result.getProductStatusEnum());
    }

    @Test
    public void findByCategory(){
        List<ProductInfo> productInfoList = productService.findByCategoryType(1);
        Assert.assertNotEquals(0 , productInfoList);

    }
}