package com.dm.shardingsphere.entity;

import lombok.Data;

import java.io.Serializable;

/**
  * @className OrderItem 
  * @description 订单实例绑定表，关联查询测试
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 5703725470393387012L;

    private long orderItemId;

    private long orderId;

    private int userId;

    private String status;

}
