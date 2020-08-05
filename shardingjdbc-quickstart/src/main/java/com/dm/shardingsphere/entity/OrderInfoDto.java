package com.dm.shardingsphere.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
  * @className OrderInfoDto 
  * @description TODO
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
@Data
@AllArgsConstructor
public class OrderInfoDto {

    private List<Order> order;

    private List<OrderItem> item;

}
