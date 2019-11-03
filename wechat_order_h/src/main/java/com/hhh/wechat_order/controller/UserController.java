package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.VO.ResultVo;
import com.hhh.wechat_order.entity.User;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.form.UserForm;
import com.hhh.wechat_order.service.UserService;
import com.hhh.wechat_order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户相关
 * @author HHH
 * @version 1.0 2019/10/18
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResultVo create(@Valid UserForm form ,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("参数不正确, userForm={}", form);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User userOld = userService.findByOpenid(form.getOpenid());
        User user = new User();
        if(userOld != null){
            user.setUserId(userOld.getUserId());
        }
        user.setUserName(form.getUsername());
        user.setOpenid(form.getOpenid());
        user.setUserPhone(form.getPhone());
        user.setUserTable(form.getUsertable());
        user.setUserNumber(form.getUsernumber());
        userService.save(user);

        return ResultVOUtil.success(user);
    }

    @GetMapping("/getUserInfo")
    public ResultVo getUserInfo(@RequestParam("openid") String openid){
        User user = userService.findByOpenid(openid);
        return ResultVOUtil.success(user);
    }
}
