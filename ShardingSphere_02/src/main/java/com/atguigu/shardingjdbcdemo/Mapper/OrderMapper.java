package com.atguigu.shardingjdbcdemo.Mapper;

import com.atguigu.shardingjdbcdemo.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}