package com.dm.shardingsphere.mapper;

import com.dm.shardingsphere.entity.TOrder;
import com.dm.shardingsphere.entity.TOrderItemFull;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("orderMapper")
public interface OrderMapper extends Mapper<TOrder> {
    @Select(" SELECT b.address_id,a.* FROM t_order_item a LEFT JOIN t_order b ON a.order_id = b.order_id")
    List<TOrderItemFull> selectFullInfo();
}
