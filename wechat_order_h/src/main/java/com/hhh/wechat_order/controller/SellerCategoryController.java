package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.entity.ProductCategory;
import com.hhh.wechat_order.entity.ProductInfo;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.form.CategoryForm;
import com.hhh.wechat_order.service.CategoryService;
import com.hhh.wechat_order.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 卖家类目Controller层
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String , Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList" , productCategoryList);
        return new ModelAndView("category/list" , map);
    }

    /**
     * 修改 / 新增页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId" , required = false) Integer categoryId ,
                              Map<String , Object> map){
        if(categoryId != null){
            Optional<ProductCategory> productCategory = categoryService.findById(categoryId);
            map.put("productCategory" , productCategory.get());
        }
        return new ModelAndView("category/index" , map);
    }

    /**
     * 新增 / 修改后 , 保存类目
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm ,
                             BindingResult bindingResult ,
                             Map<String  , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/seller/category/index");
            return new ModelAndView("common/error" , map);
        }
        try {
            if(categoryForm.getCategoryId() != null){
                Optional<ProductCategory> productCategory = categoryService.findById(categoryForm.getCategoryId());
                List<ProductInfo> productInfoList = productService.findByCategoryType(productCategory.get().getCategoryType());
                BeanUtils.copyProperties(categoryForm , productCategory.get());
                categoryService.save(productCategory.get());
                for(ProductInfo productInfo : productInfoList){
                    productInfo.setCategoryType(productCategory.get().getCategoryType());
                    productService.save(productInfo);
                }
            }else{
                ProductCategory productCategory = new ProductCategory();
                BeanUtils.copyProperties(categoryForm , productCategory);
                categoryService.save(productCategory);
            }
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/category/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "类目操作成功！");
        map.put("url" , "/sell/seller/category/list");
        return new ModelAndView("common/success" , map);
    }

    //删除类目
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("categoryId") Integer categoryId ,
                               Map<String , Object> map){
        try {
            categoryService.delete(categoryId);
        }catch (SellException e){
            map.put("msg" , "还有商品属于该类目 , 不能删除该类目！");
            map.put("url" , "/sell/seller/category/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "类目删除成功！");
        map.put("url" , "/sell/seller/category/list");
        return new ModelAndView("common/success" , map);
    }
}
