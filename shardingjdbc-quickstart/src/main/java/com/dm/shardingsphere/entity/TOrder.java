package com.dm.shardingsphere.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
  * @className Order 
  * @description 订单实例
  * @author dm
  * @date 2020/8/5
  * @since JDK1.8
  */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TOrder implements Serializable {

    private static final long serialVersionUID = -649792992431595396L;

    @Id
    private Long orderId;

    private Long userId;

    private Long addressId;

    private String status;

}
