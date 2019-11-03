package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.constant.CookieConstant;
import com.hhh.wechat_order.constant.RedisConstant;
import com.hhh.wechat_order.entity.SellerInfo;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.form.SellerForm;
import com.hhh.wechat_order.repository.SellerInfoRepository;
import com.hhh.wechat_order.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 商家操作的Controller层
 * @author HHH
 * @version 1.0 2019/10/17
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminUserController {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @GetMapping("/loginAdmin")
    public String loginAdmin(@RequestParam("phone") String phone ,
                             @RequestParam("password") String password ,
                             HttpServletResponse response){
        SellerInfo sellerInfo = sellerInfoRepository.findByPhone(phone);
        log.info("【商家登录】 商家信息={}" , sellerInfo);
        if(sellerInfo != null && sellerInfo.getPassword().equals(password)){
            String token = UUID.randomUUID().toString();
            log.info("【商家登录】 登录成功的token = {}" , token);
            Integer expire = RedisConstant.EXPIRE;
            //设置token至cookie
            CookieUtil.set(response , CookieConstant.TOKEN , token , expire);
            return "登录成功";
        }else{
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request ,
                               HttpServletResponse response ,
                               Map<String , Object> map){
        //从cookie里查询
        Cookie cookie = CookieUtil.get(request , CookieConstant.TOKEN);
        if(cookie != null){
            //清除cookie
            CookieUtil.set(response , CookieConstant.TOKEN , null , 0);
        }
        map.put("msg" , ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url" , "/sell/seller/order/list");
        return new ModelAndView("common/success" , map);
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String , Object> map){
        List<SellerInfo> sellerInfoList = sellerInfoRepository.findAll();
        map.put("sellerInfoList" , sellerInfoList);
        return new ModelAndView("admin/list" , map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "sellerId" , required = false) Integer sellerId ,
                              Map<String , Object> map){
        if(!StringUtils.isEmpty(sellerId)){
            SellerInfo sellerInfo = sellerInfoRepository.findBySellerId(sellerId);
            map.put("sellerInfo" , sellerInfo);
        }
        return new ModelAndView("admin/index" , map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid SellerForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/admin/index");
            return new ModelAndView("common/error" , map);
        }
        SellerInfo sellerInfo = new SellerInfo();
        try{
            if(form.getSellerId() != null){
                sellerInfo = sellerInfoRepository.findBySellerId(form.getSellerId());
            }
            BeanUtils.copyProperties(form , sellerInfo);
            sellerInfoRepository.save(sellerInfo);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/admin/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg" , "操作成功!");
        map.put("url" , "/sell/admin/list");
        return new ModelAndView("common/success" , map);
    }
}
