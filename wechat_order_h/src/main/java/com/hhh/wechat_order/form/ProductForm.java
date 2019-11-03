package com.hhh.wechat_order.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 表单提交的字段
 */
@Data
public class ProductForm {

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品图片
    private String productIcon;

    //商品描述
    private String productDescription;

    //类目编号
    private Integer categoryType;
}
