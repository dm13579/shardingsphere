package com.dm.shardingsphere.algorithem;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * ,;,,;
 * ,;;'(
 * __      ,;;' ' \
 * /'  '\'~~'~' \ /'\.)
 * ,;(      )    /  |.
 * ,;' \    /-.,,(   ) \
 * ) /       ) / )|
 * ||        ||  \)
 * (_\       (_\
 *
 * @author dm
 * @version 1.0
 * @className TableStrategyStandardRangeAlgorithm
 * @description 范围分片算法类名
 * @date 2021/2/5 15:27
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
public class TableStrategyStandardRangeAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        // 数据节点
        System.out.println("TableStrategyStandardRangeAlgorithm:=====================================================");
        System.out.println("availableTargetNames：" + String.join(";", availableTargetNames));
        // 逻辑表名 t_order
        String logicTableName = shardingValue.getLogicTableName();
        // 分片键名 order_id
        String columnName = shardingValue.getColumnName();
        // 传进来的 查询范围，根据范围可以定制分片策略，指定查询表
        Long start = shardingValue.getValueRange().lowerEndpoint();
        Long end = shardingValue.getValueRange().upperEndpoint();

        System.out.println("logicTableName：" + logicTableName + ";columnName：" + columnName + ";start：" + start + ";end：" + end);

        // 确认范围
        // 这里直接返回所有,可以根据start,end定制范围，比如表一0,500表二501-1000这里返回对应表就可以了
        return availableTargetNames;
        // 这里表示范围查询只能查t_order_1表
//        return Collections.singletonList("t_order_1");
    }
}
