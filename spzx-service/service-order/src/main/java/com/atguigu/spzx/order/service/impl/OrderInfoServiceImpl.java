package com.atguigu.spzx.order.service.impl;

import com.atguigu.spzx.feign.cart.CartFeignClient;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.feign.user.UserFeignClient;
import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.entity.order.OrderItem;
import com.atguigu.spzx.model.entity.order.OrderLog;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.order.TradeVo;
import com.atguigu.spzx.order.mapper.OrderInfoMapper;
import com.atguigu.spzx.order.mapper.OrderItemMapper;
import com.atguigu.spzx.order.mapper.OrderLogMapper;
import com.atguigu.spzx.order.service.OrderInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private ProductFeignClient productFeignClient;


    @Override
    public TradeVo trade() {

        UserInfo userInfo = UserInfoAuthContextUtil.get();
        List<CartInfo> cartInfoList = cartFeignClient.getAllCkecked().getData();

        // 将购物车数据转化为orderItem
        List<OrderItem> orderItemList = new ArrayList<>();
        cartInfoList.stream().map(cartInfo -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItemList.add(orderItem);
            return orderItem;
        }).collect(Collectors.toList());

        // 总金额
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalPrice = totalPrice.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }

        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalPrice);
        return tradeVo;
    }

    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        UserInfo userInfo = UserInfoAuthContextUtil.get();

        // 生成订单数据
        OrderInfo orderInfo = new OrderInfo();
        String OrderNum = String.valueOf(System.currentTimeMillis());
        orderInfo.setOrderNo(OrderNum);
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setNickName(userInfo.getNickName());
        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderItem orderItem : orderInfoDto.getOrderItemList()) {
            totalPrice = totalPrice.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }

        orderInfo.setTotalAmount(totalPrice);
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());
        orderInfo.setReceiverAddress(userAddress.getAddress());
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalPrice);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);

        orderInfoMapper.insertOrder(orderInfo);
        Long orderInfoId = orderInfo.getId();

        // 生成订单详情数据
//        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
//        for (OrderItem orderItem : orderItemList) {
//            orderItem.setOrderId(orderInfoId);
//            orderItemMapper.insertOrderItem(orderItem);
//        }
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfoId);
            orderItemMapper.insertOrderItem(orderItem);
        }

        // 删除购物车信息
        cartFeignClient.deleteChecked();

        // 记录订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfoId);
        orderLog.setNote("订单创建成功");
//        orderLog.setCreateTime(new Date());
        orderLog.setOperateUser(userInfo.getUsername());
        orderLog.setProcessStatus(0);
        orderLogMapper.insertOrderLog(orderLog);

        // 返回订单id
        return orderInfoId;
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        return orderInfo;
    }

    @Override
    public TradeVo buy(Long skuId) {
        TradeVo tradeVo = new TradeVo();
        // 获取productSku
        ProductSku productSku = productFeignClient.getBySkuId(skuId).getData();

        tradeVo.setTotalAmount(productSku.getSalePrice());

        OrderItem orderItem = new OrderItem();

        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setSkuNum(1);
        orderItem.setCreateTime(new Date());
        orderItem.setUpdateTime(new Date());
        orderItem.setIsDeleted(0);


        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }

    @Override
    public PageInfo<OrderInfo> list(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page, limit);
        Long userId = UserInfoAuthContextUtil.get().getId();
        List<OrderInfo> orderInfos = orderInfoMapper.selectOrderPage(userId,orderStatus);
        return new PageInfo<>(orderInfos);
    }

    @Override
    public OrderInfo getByOrderNo(String orderNo) {
        OrderInfo orderInfo = orderInfoMapper.selectByOrderNo(orderNo);
        return orderInfo;
    }
}
