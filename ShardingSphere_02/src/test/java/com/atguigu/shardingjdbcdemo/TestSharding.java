package com.atguigu.shardingjdbcdemo;

import com.atguigu.shardingjdbcdemo.Mapper.OrderMapper;
import com.atguigu.shardingjdbcdemo.Mapper.UserMapper;
import com.atguigu.shardingjdbcdemo.entity.Order;
import com.atguigu.shardingjdbcdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSharding {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setUname("奥特曼");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("ATGUIGU001");
        order.setUserId(user.getId());
        orderMapper.insert(order);
    }

    @Test
    public void selectTest(){
        Order order = orderMapper.selectById(1);
        User user = userMapper.selectById(1);
        System.out.println(order);
        System.out.println(user);
    }

    @Test
    public void updateTest(){
        Order order = orderMapper.selectById(1);
        User user = userMapper.selectById(1);
        user.setUname("David");
        int rows = userMapper.updateById(user);
        if(rows > 0){
            System.out.println("update success");
        }else {
            System.out.println("update fail");
        }
        System.out.println(order);
        System.out.println(user);
    }
}
