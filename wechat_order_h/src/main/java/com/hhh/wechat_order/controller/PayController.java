package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.VO.ResultVo;
import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.enums.OrderStatusEnum;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.service.OrderService;
import com.hhh.wechat_order.service.impl.PayService;
import com.hhh.wechat_order.utils.ResultVOUtil;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 支付
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("/goPay")
    public ResultVo<Boolean> goPay(@RequestParam("orderId") String orderId){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付
        OrderDTO orderDTO1 = payService.goPay(orderDTO);
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_PAYED.getCode())){
            log.error("【支付订单】订单状态不正确,  orderStatus={}", orderDTO1.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        return ResultVOUtil.success(true);
    }
}
