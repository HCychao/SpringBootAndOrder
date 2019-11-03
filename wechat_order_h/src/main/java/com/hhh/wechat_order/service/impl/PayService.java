package com.hhh.wechat_order.service.impl;

import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.entity.OrderMaster;
import com.hhh.wechat_order.enums.OrderStatusEnum;
import com.hhh.wechat_order.enums.PayStatusEnum;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.repository.OrderMasterRepository;
import com.hhh.wechat_order.service.OrderService;
import com.hhh.wechat_order.utils.JsonUtil;
import com.hhh.wechat_order.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 支付
 */
@Service
@Slf4j
public class PayService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    public OrderDTO goPay(OrderDTO orderDTO){
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(),
                    orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.NEW_PAYED.getCode());
        //TODO
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【支付订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    //退款
    public RefundResponse refund(OrderDTO orderDTO){
        return null;
    }
}
