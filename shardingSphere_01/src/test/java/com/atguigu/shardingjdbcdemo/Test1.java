package com.atguigu.shardingjdbcdemo;


import com.atguigu.shardingjdbcdemo.entity.User;
import com.atguigu.shardingjdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test1 {

    @Autowired
    UserMapper userMapper;

    @Test
    public void a(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelect(){

        for (int i = 0; i < 100; i++) {
            User user1 = userMapper.selectById(1);
        }
    }

}


