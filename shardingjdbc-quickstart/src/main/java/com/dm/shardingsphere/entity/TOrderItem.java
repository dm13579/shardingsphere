package com.dm.shardingsphere.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  * @className OrderItem 
  * @description 订单实例绑定表，关联查询测试
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TOrderItem implements Serializable {

    private static final long serialVersionUID = 5703725470393387012L;

    private Long orderItemId;

    private Long orderId;

    private Long userId;

    private String status;

}
