package com.dm.shardingsphere.service.impl;

import com.dm.shardingsphere.entity.Order;
import com.dm.shardingsphere.entity.OrderInfoDto;
import com.dm.shardingsphere.entity.OrderItem;
import com.dm.shardingsphere.mapper.OrderItemMapper;
import com.dm.shardingsphere.mapper.OrderMapper;
import com.dm.shardingsphere.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
  * @className OrderServiceImpl 
  * @description 订单service
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;


    @Override
    public long confirmOrder(int sequenceId) {
        //创建订单
        Order order = new Order();
        order.setAddressId(sequenceId);
        order.setUserId(sequenceId);
        order.setStatus("创建订单");
        try {
            orderMapper.insert(order);
            //订单对应产品
            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(sequenceId);
            item.setOrderItemId(sequenceId);
            item.setStatus("创建订单");
            orderItemMapper.insert(item);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
            throw new RuntimeException("SQLException",e.getCause());
        }
        return order.getOrderId();
    }

    @Override
    public OrderInfoDto selectAll() {
        try {
            return new OrderInfoDto(orderMapper.selectAll(),orderItemMapper.selectAll());
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }

    @Override
    public String deleteData(long orderId) {
        try {
            orderMapper.delete(orderId);
            orderItemMapper.delete(orderId);
            return "delete data success";
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return "failure";
    }

    @Override
    public int updateOrder(long orderId, String status) {
        try {
            return orderMapper.update(orderId,status);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
            throw new RuntimeException("exception is happenning, tx will be rollback",e.getCause());
        }
    }

    @Override
    public OrderInfoDto selectOrderRange(long start, long end) {
        try {
            return new OrderInfoDto(orderMapper.selectRange(start,end),null);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }

    @Override
    public OrderInfoDto selectOrderItemRange(int start, int end) {
        try {
            return new OrderInfoDto(null,orderItemMapper.selectRange(start,end));
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }

    @Override
    public OrderInfoDto selectOrderItemWithIn(long start, long end) {
        try {
            return new OrderInfoDto(null,orderItemMapper.selectWithInCondition(start,end));
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }

    @Override
    public OrderInfoDto selectOrderPageList(long offset, long size) {
        try {
            return new OrderInfoDto(orderMapper.selectRange(offset,size),null);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }
}
