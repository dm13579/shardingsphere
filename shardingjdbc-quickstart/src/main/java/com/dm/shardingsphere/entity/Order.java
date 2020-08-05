package com.dm.shardingsphere.entity;

import lombok.Data;

import java.io.Serializable;

/**
  * @className Order 
  * @description 订单实例
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -649792992431595396L;

    private long orderId;

    private int userId;

    private long addressId;

    private String status;

}
