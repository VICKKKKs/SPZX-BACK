package com.atguigu.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_order")
@Data
public class Order {
    //@TableId(type = IdType.AUTO)//依赖数据库的主键自增策略
    @TableId(type = IdType.ASSIGN_ID)//分布式id
    private Long id;
    private String orderNo;
    private Long userId;
}
