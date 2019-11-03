package com.hhh.wechat_order.handler;

import com.hhh.wechat_order.VO.ResultVo;
import com.hhh.wechat_order.config.ProjectUrlConfig;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.exception.SellerAuthorizeException;
import com.hhh.wechat_order.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理登录异常
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
//        return new ModelAndView("redirect:"
//                .concat(projectUrlConfig.getWechatOpenAuthorize())
//                .concat("/sell/wechat/qrAuthorize")
//                .concat("?returnUrl=")
//                .concat(projectUrlConfig.getSell())
//                .concat("/sell/seller/login"));
        return new ModelAndView("common/loginView");
    }

//    @ExceptionHandler(value = SellException.class)
//    @ResponseBody
//    public ResultVo handlerSellerException(SellException e){
//        return ResultVOUtil.error(e.getCode() , e.getMessage());
//    }


}
