package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.entity.ProductCategory;
import com.hhh.wechat_order.entity.ProductInfo;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.form.ProductForm;
import com.hhh.wechat_order.service.CategoryService;
import com.hhh.wechat_order.service.ProductService;
import com.hhh.wechat_order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 卖家端商品Controller层
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 卖家的商品列表
     * @param page  第几页，从第一页开始
     * @param size  一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                             @RequestParam(value = "size" , defaultValue = "5") Integer size ,
                             Map<String , Object> map){
        PageRequest pageRequest = new PageRequest(page - 1 , size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage" , productInfoPage);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("product/list" , map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品上架成功");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品下架成功");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 添加或者修改商品的页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId" , required = false) String productId ,
                              Map<String , Object> map){
        if( !StringUtils.isEmpty(productId)){
            Optional<ProductInfo> productInfo = productService.findById(productId);
            map.put("productInfo" , productInfo.get());
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList" , categoryList);
        return new ModelAndView("product/index" ,map);
    }

    /**
     * 保存/更新 商品
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/seller/product/index");
            return new ModelAndView("common/error" , map);
        }
        try{
            //TODO
            //如果 productId 为空 , 说明是新增商品
            if(StringUtils.isEmpty(form.getProductId())){
                ProductInfo productInfo = new ProductInfo();
                form.setProductId(KeyUtil.genUniqueKey());
                BeanUtils.copyProperties(form , productInfo);
                productService.save(productInfo);
            }else{
                Optional<ProductInfo> productInfo = productService.findById(form.getProductId());
                BeanUtils.copyProperties(form , productInfo.get());
                productService.save(productInfo.get());
            }
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品操作成功");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productService.delete(productId);
        }catch (SellException e){
            map.put("msg" , "商品还在售卖中，不能删除该商品！");
            map.put("url" , "/sell/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品删除成功！");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }
}
