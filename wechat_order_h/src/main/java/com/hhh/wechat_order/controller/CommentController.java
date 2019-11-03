package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.VO.ResultVo;
import com.hhh.wechat_order.dto.OrderDTO;
import com.hhh.wechat_order.entity.Comment;
import com.hhh.wechat_order.entity.OrderMaster;
import com.hhh.wechat_order.enums.OrderStatusEnum;
import com.hhh.wechat_order.enums.ResultEnum;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.repository.CommentRepository;
import com.hhh.wechat_order.repository.OrderMasterRepository;
import com.hhh.wechat_order.service.OrderService;
import com.hhh.wechat_order.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 评论的Controller层
 * @author HHH
 * @version 1.0 2019/10/17
 */
@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMasterRepository masterRepository;

    //订单详情
    @PostMapping("/comment")
    public ResultVo<Comment> detail(@RequestParam("openid") String openid ,
                                    @RequestParam("orderId") String orderId ,
                                    @RequestParam("username") String username ,
                                    @RequestParam("avatarUrl") String avatarUrl ,
                                    @RequestParam("content") String content){
        if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        //提交评论
        Comment comment = new Comment();
        comment.setCommentName(username);
        comment.setAvatarUrl(avatarUrl);
        comment.setOpenid(openid);
        comment.setCommentContent(content);
        Comment save = commentRepository.save(comment);

        //修改订单状态
        OrderDTO orderDTO = orderService.findOne(orderId);
        orderDTO.setOrderStatus(OrderStatusEnum.COMMENT.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        return ResultVOUtil.success(save);
    }

    //所有评论
    @GetMapping("/commentList")
    public ResultVo<List<Comment>> commentList(){
        List<Comment> all = commentRepository.findAll();
        return ResultVOUtil.success(all);
    }

    //单个用户的所有评论
    @GetMapping("/userCommentList")
    public ResultVo<List<Comment>> userCommentList(@RequestParam("openid") String openid){
        List<Comment> all =commentRepository.findAllByOpenid(openid);
        return ResultVOUtil.success(all);
    }
}
