package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 图片的 DAO 接口
 * @author HHH
 * @version 1.0 2019/10/17
 */
public interface PictureRepository extends JpaRepository<Picture , Integer> {

    /**
     * 通过 picId 查询该图片
     * @param picId
     * @return Picture 对象
     */
    Picture findByPicId(Integer picId);
}
