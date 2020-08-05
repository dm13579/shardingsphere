package com.dm.shardingsphere.service;

import com.dm.shardingsphere.entity.OrderInfoDto;

/**
  * @interfaceName OrderService 
  * @description TODO
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
public interface OrderService {

    long confirmOrder(int sequenceId);

    OrderInfoDto selectAll();

    String deleteData(long orderId);

    int updateOrder(long orderId,String status);

    OrderInfoDto selectOrderRange(long start,long end);

    OrderInfoDto selectOrderItemRange(int start,int end);

    OrderInfoDto selectOrderItemWithIn(long start,long end);

    OrderInfoDto selectOrderPageList(long offset,long size);

}
