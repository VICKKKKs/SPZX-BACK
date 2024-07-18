package com.atguigu.shardingjdbcdemo;

import com.atguigu.shardingjdbcdemo.Mapper.OrderMapper;
import com.atguigu.shardingjdbcdemo.Mapper.UserMapper;
import com.atguigu.shardingjdbcdemo.entity.Order;
import com.atguigu.shardingjdbcdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLevel {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void test(){

        User user = new User();
        user.setUname("Jack");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("CyberPunk01");
        order.setUserId(user.getId());
        orderMapper.insert(order);

        System.out.println(user);
        System.out.println(order);
    }

    /**
     * 水平分片：分库插入数据测试
     */
    @Test
    public void testInsertOrderDatabaseStrategy(){

        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("CyberPunk" + System.currentTimeMillis());
            order.setUserId(i + 1);
            orderMapper.insert(order);
        }
    }

    /*
    水平分库分表
     */
    @Test
    public void testInsertOrderTableStrategy(){

        for (long i = 0; i < 4; i++) {

            Order order = new Order();
            order.setOrderNo("CyberPunk" + System.currentTimeMillis());
            order.setUserId(1L);// 四个数据都进入order1库
            System.out.println("进入order1库的4条数据");
            orderMapper.insert(order);
            if(order.getId()%2==0){
                System.out.println("在order1库的基础上，主键是偶数，进入t0表"+order.getId());
            }else {
                System.out.println("在order1库的基础上，主键是奇数，进入t1表"+order.getId());
            }

        }

        for (long i = 0; i < 4; i++) {

            Order order = new Order();
            order.setOrderNo("CyberPunk" + System.currentTimeMillis());
            order.setUserId(2L);// 四个数据都进入order0库
            System.out.println("进入order0库的4条数据");
            orderMapper.insert(order);
            if(order.getId()%2==0){
                System.out.println("在order1库的基础上，主键是偶数，进入t0表"+order.getId());
            }else {
                System.out.println("在order1库的基础上，主键是奇数，进入t1表"+order.getId());
            }
        }
    }

}
