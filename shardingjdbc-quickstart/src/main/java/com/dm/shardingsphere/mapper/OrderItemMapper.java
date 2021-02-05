package com.dm.shardingsphere.mapper;

import com.dm.shardingsphere.entity.TOrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("orderItemMapper")
public interface OrderItemMapper extends Mapper<TOrderItem> {

}
