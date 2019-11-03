package com.hhh.wechat_order.controller;

import com.hhh.wechat_order.VO.ResultVo;
import com.hhh.wechat_order.entity.Picture;
import com.hhh.wechat_order.exception.SellException;
import com.hhh.wechat_order.form.PictureForm;
import com.hhh.wechat_order.repository.PictureRepository;
import com.hhh.wechat_order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 微信用户端的图片相关操作的Controller
 * @author HHH
 * @version 1.0 2019/10/17
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/getAll")
    public ResultVo getUserInfo(){
        List<Picture> pictures = pictureRepository.findAll();
        return ResultVOUtil.success(pictures);
    }

    //页面相关
    //TODO
    @GetMapping("/list")
    public ModelAndView list(Map<String , Object> map){
        List<Picture> pictures = pictureRepository.findAll();
        map.put("pictures" , pictures);
        return new ModelAndView("picture/list" , map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "picId" , required = false) Integer picId ,
                              Map<String , Object> map){
        if(picId != null){
            Picture picture = pictureRepository.findByPicId(picId);
            map.put("picture" , picture);
        }
        return new ModelAndView("picture/index" , map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PictureForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/picture/index");
            return new ModelAndView("common/error" , map);
        }
        Picture picture = new Picture();
        try{
            if(form.getPicId() != null){
                picture = pictureRepository.findByPicId(form.getPicId());
            }
            BeanUtils.copyProperties(form , picture);
            pictureRepository.save(picture);
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/picture/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "操作成功！");
        map.put("url", "/sell/picture/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("picId") Integer picId , Map<String , Object> map){
        Picture picture = pictureRepository.findByPicId(picId);
        pictureRepository.delete(picture);
        map.put("msg" , "图片删除成功！");
        map.put("url" , "/sell/picture/list");
        return new ModelAndView("common/success" , map);
    }
}
