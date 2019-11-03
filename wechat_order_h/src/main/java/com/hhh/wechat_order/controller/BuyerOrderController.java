package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.VO.ResultVo;
import com.hhh.wechat_order.converter.OrderForm2OrderDTOConverter;
import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.form.OrderForm;
import com.hhh.wechat_order.service.BuyerService;
import com.hhh.wechat_order.service.OrderService;
import com.hhh.wechat_order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单的Controller层
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm , BindingResult bindingResult){

            //判断表单校验有无错误
            if(bindingResult.hasErrors()){
                log.error("【创建订单】 参数不正确 , orderForm={}" , orderForm);
                throw new SellException(ResultEnum.PARAM_ERROR.getCode() ,
                        bindingResult.getFieldError().getDefaultMessage());
            }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.conver(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId" , createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo list(@RequestParam("openid") String openid ,
                         @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                         @RequestParam(value = "size" , defaultValue = "5") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page , size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    @GetMapping("/listByStatus")
    public ResultVo<List<OrderDTO>> listByStatus(@RequestParam("openid") String openid ,
                                                 @RequestParam(value="orderStatus" , defaultValue = "0") Integer orderStatus){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        List<OrderDTO> orderList = buyerService.findOrderList(openid , orderStatus);
        return ResultVOUtil.success(orderList);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid ,
                                     @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //确认收货
    //TODO
//    @PostMapping("/sure")
//    public ResultVo sure(@RequestParam("openid") String openid ,
//                         @RequestParam("orderId") String orderId){
//        buyerService.cancelOrder(openid , orderId);
//        return ResultVOUtil.success();
//    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid ,
                     @RequestParam("orderId") String orderId){
        buyerService.cancelOrder(openid , orderId);
        return ResultVOUtil.success();
    }
}
