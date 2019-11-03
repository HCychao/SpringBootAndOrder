package com.hhh.wechat_order.repository;

import com.hhh.wechat_order.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("HHH");
        orderMaster.setBuyerPhone("17875303280");
        orderMaster.setBuyerAddress("嘉应学院");
        orderMaster.setBuyerOpenid("abc23");
        orderMaster.setOrderAmount(new BigDecimal(25.7));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderMaster> pageOrder = repository.findByBuyerOpenid("abc123", pageRequest);
        System.out.println(pageOrder.getTotalElements());//获取总的条数
        Assert.assertNotEquals(0 , pageOrder.getTotalElements());
    }
}