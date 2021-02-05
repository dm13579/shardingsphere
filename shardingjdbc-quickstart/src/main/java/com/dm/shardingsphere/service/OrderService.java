package com.dm.shardingsphere.service;

import com.dm.shardingsphere.entity.TOrder;
import com.dm.shardingsphere.entity.TOrderItemFull;
import com.dm.shardingsphere.mapper.OrderItemMapper;
import com.dm.shardingsphere.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dm
 * @className OrderServiceImpl
 * @description 订单service
 * @date 2020/8/5
 * @since JDK1.8
 */
@Slf4j
@Service("orderService")
public class OrderService {

    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;

    @Resource(name = "orderItemMapper")
    private OrderItemMapper orderItemMapper;

    /**
     * 插入测试，看插入数据是否根据分片规则插到不同的库和表
     */
    public void insertOrderTest() {
        for (int i = 0; i < 10; i++) {
            orderMapper.insertSelective(TOrder.builder()
                    .addressId((long) i)
                    .userId((long) i)
                    .status("创建订单")
                    .build());
        }
    }

    /**
     * 查询数据，看是否查询到所有数据
     */
    public List<TOrder> queryAllTest() {
        return orderMapper.selectAll();
    }

    /**
     * 主键id查询
     */
    public TOrder queryByOrderIdTest(long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    /**
     * addressId查询，非分片键查询
     */
    public List<TOrder> queryByAddressIdTest(long addressId) {
        return orderMapper.select(TOrder.builder().userId((long) 1).addressId(addressId).build());
    }

    /**
     * orderId范围查询
     */
    public List<TOrder> queryOrderIdRange(long start, long end) {
        Example example = new Example(TOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("orderId", start, end);
        return orderMapper.selectByExample(example);
    }

    public List<TOrder> queryOrderByHint() {
        HintManager hintManager = HintManager.getInstance();
        try {
            hintManager.addTableShardingValue("t_order", (long)1);
            return orderMapper.select(TOrder.builder().userId((long) 1).build());
        } finally {
            hintManager.close();
        }
    }

    public List<TOrderItemFull> queryLeftJoinTest() {
       return orderMapper.selectFullInfo();

    }
//    @Override
//    public long confirmOrder(long sequenceId) {
//        //创建订单
//        TOrder tOrder = TOrder.builder()
//                .addressId(sequenceId)
//                .userId(sequenceId)
//                .status("创建订单")
//                .build();
//        try {
//            orderMapper.insertSelective(tOrder);
//
//            orderItemMapper.insertSelective(TOrderItem.builder()
//                    .orderId(tOrder.getOrderId())
//                    .userId(sequenceId)
//                    .status("创建订单")
//                    .build());
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//            throw new RuntimeException("SQLException", e.getCause());
//        }
//        return tOrder.getOrderId();
//    }
//
//    @Override
//    public OrderInfoDto selectAll() {
//        try {
//            return new OrderInfoDto(orderMapper.selectAll(), orderItemMapper.selectAll());
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//        }
//        return null;
//    }
//
//    @Override
//    public String deleteData(long orderId) {
//        try {
//            orderMapper.deleteByPrimaryKey(orderId);
//            orderItemMapper.deleteByPrimaryKey(orderId);
//            return "delete data success";
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//        }
//        return "failure";
//    }
//
//    @Override
//    public int updateOrder(long orderId, String status) {
//        try {
//            return orderMapper.updateByPrimaryKey(TOrder.builder().orderId(orderId).status(status).build());
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//            throw new RuntimeException("exception is happenning, tx will be rollback", e.getCause());
//        }
//    }
//
//    @Override
//    public OrderInfoDto selectOrderRange(long start, long end) {
//        try {
//            Example example = new Example(TOrder.class);
//            Example.Criteria criteria = example.createCriteria();
//            criteria.andBetween("order_id", start, end);
//            return new OrderInfoDto(orderMapper.selectByExample(example), null);
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//        }
//        return null;
//    }
//
//    @Override
//    public OrderInfoDto selectOrderItemRange(int start, int end) {
//        try {
//            return new OrderInfoDto(null, orderItemMapper.selectRange(start, end));
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//        }
//        return null;
//    }
//
//    @Override
//    public OrderInfoDto selectOrderItemWithIn(long start, long end) {
//        try {
//            return new OrderInfoDto(null, orderItemMapper.selectWithInCondition(start, end));
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//        }
//        return null;
//    }
//
//    @Override
//    public OrderInfoDto selectOrderPageList(int page, int limit) {
//        try {
////            PageHelper.startPage(page, limit);
//            List<TOrder> orders = orderMapper.selectAll();
////            PageInfo<Order> pageInfo = new PageInfo<>(orders);
//            return new OrderInfoDto(orders, null);
//        } catch (Exception e) {
//            log.info(e.getMessage(), e.getCause());
//        }finally {
////            PageHelper.clearPage();
//        }
//        return null;
//    }
}
