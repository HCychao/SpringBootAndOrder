package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.service.OrderService;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *卖家端的订单 Controller 层
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 卖家的订单列表
     * @param page  第几页，从第一页开始
     * @param size  一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                             @RequestParam(value = "size" , defaultValue = "10") Integer size ,
                             Map<String , Object> map){
        PageRequest pageRequest = new PageRequest(page - 1 , size);
//        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        Page<OrderDTO> orderDTOPage = orderService.findOrderMastersByCreateTimeDesc(pageRequest);
        map.put("orderDTOPage" , orderDTOPage);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("order/list" , map);
    }

    @PostMapping("/search")
    public ModelAndView search(HttpServletRequest request ,
                               HttpServletResponse response ,
                               Map<String , Object> map) throws IOException {
        String orderId = request.getParameter("search");
        if (orderId == null || orderId == ""){
//            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
//            map.put("url", "/sell/seller/order/list");
//            return new ModelAndView("common/error", map);
            response.sendRedirect(request.getContextPath() + "/seller/order/list");
            return null;
        }
        OrderDTO orderDTO = orderService.findOne(orderId);
        map.put("orderDTO" , orderDTO);
        return new ModelAndView("order/list1" , map);
    }

    /**
     * 取消订单
     * @param orderId 订单的ID
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId ,
                               Map<String , Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】 发生异常{}" , e);
            map.put("msg" , ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url" , "/sell/seller/order/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url" , "/sell/seller/order/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId ,
                               Map<String , Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【卖家端查询订单详情】 发生异常{}", e);
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO" , orderDTO);
        return new ModelAndView("order/detail", map);
    }

    /**
     * 订单完结
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId ,
                               Map<String , Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】 发生异常{}", e);
            map.put("msg" , ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url" , "/sell/seller/order/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url" , "/sell/seller/order/list");
        return new ModelAndView("common/success" , map);
    }
}
