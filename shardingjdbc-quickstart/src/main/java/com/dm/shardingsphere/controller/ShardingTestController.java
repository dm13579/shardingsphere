package com.dm.shardingsphere.controller;

import com.dm.shardingsphere.entity.TOrder;
import com.dm.shardingsphere.entity.TOrderItemFull;
import com.dm.shardingsphere.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class ShardingTestController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @GetMapping("/insertOrderTest")
    public String insertOrderTest(){
        orderService.insertOrderTest();
        return "创建订单成功";
    }

    @GetMapping("/queryAllTest")
    public List<TOrder> queryAllTest(){
        return orderService.queryAllTest();
    }

    @GetMapping("/queryByOrderIdTest")
    public TOrder queryByOrderIdTest(long orderId){
        return orderService.queryByOrderIdTest(orderId);
    }

    @GetMapping("/queryByAddressIdTest")
    public List<TOrder>  queryByAddressIdTest(long addressId){
        return orderService.queryByAddressIdTest(addressId);
    }

    @GetMapping("/queryOrderIdRange")
    public List<TOrder>  queryOrderIdRange(long start, long end){
        return orderService.queryOrderIdRange(start, end);
    }

    @GetMapping("/queryOrderByHint")
    public List<TOrder>  queryOrderByHint(){
        return orderService.queryOrderByHint();
    }

    @GetMapping("/queryLeftJoinTest")
    public List<TOrderItemFull>  queryLeftJoinTest(){
        return orderService.queryLeftJoinTest();
    }

//    @GetMapping("/confirm_order")
//    public String confirmOrder(long sequenceId) {
//        long id = orderService.confirmOrder(sequenceId);
//        return "创建订单成功:订单ID = " + id;
//    }
//
//    @GetMapping("/order_histroy_list")
//    public OrderInfoDto orderHistoryList() {
//        return orderService.selectAll();
//    }
//
//    /**
//     * 删除历史订单
//     *
//     * @param orderId 订单编号
//     */
//    @GetMapping("/delete_histroy_order")
//    public String deleteHistroyOrder(long orderId) {
//        return orderService.deleteData(orderId);
//    }
//
//    /**
//     * 更改历史订单状态
//     *
//     * @param orderId 订单编号
//     * @param status 状态
//     */
//    @GetMapping("/update_histroy_order")
//    public int updateHistoryOrderStatus(long orderId, String status) {
//        return orderService.updateOrder(orderId, status);
//    }
//
//    /**
//     * range orderid {200000000000000000 - 400000000000000000}
//     *
//     * @param start 开始
//     * @param end 结束
//     */
//    @GetMapping("/order_range_list")
//    public OrderInfoDto orderRangeList(long start, long end) {
//        return orderService.selectOrderRange(start, end);
//    }
//
//    /**
//     * range userid {1-20}
//     *
//     * @param start 开始
//     * @param end 结束
//     */
//    @GetMapping("/item_range_list")
//    public OrderInfoDto orderItemRangeList(int start, int end) {
//        return orderService.selectOrderItemRange(start, end);
//    }
//
//    /**
//     * 笛卡尔积测试
//     *
//     * @param start 开始
//     * @param end 结束
//     */
//    @GetMapping("/item_range_in_list")
//    public OrderInfoDto orderItemRangeInList(long start, long end) {
//        return orderService.selectOrderItemWithIn(start, end);
//    }
//
//    /**
//     * 分页测试
//     */
//    @GetMapping("/item_page_list")
//    public OrderInfoDto orderPageList(int page, int size) {
//        return orderService.selectOrderPageList(page, size);
//    }

}
