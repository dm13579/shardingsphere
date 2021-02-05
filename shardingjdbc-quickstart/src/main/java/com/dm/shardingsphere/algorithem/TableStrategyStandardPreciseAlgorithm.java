package com.dm.shardingsphere.algorithem;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author dm
 * @version 1.0
 * @className TableStrategyStandardPreciseAlgorithm
 * @description 精确分片算法类名
 * @date 2021/2/5 15:27
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
public class TableStrategyStandardPreciseAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // 数据节点
        System.out.println("TableStrategyStandardPreciseAlgorithm:=====================================================");
        System.out.println("availableTargetNames：" + String.join(";", availableTargetNames));
        // 逻辑表名 t_order
        String logicTableName = shardingValue.getLogicTableName();
        // 分片键名 order_id
        String columnName = shardingValue.getColumnName();
        // 传进来的 order_id值，根据这个值可以定制分片策略
        Long value = shardingValue.getValue();
        System.out.println("logicTableName：" + logicTableName + ";columnName：" + columnName + ";value：" + value);

        // 确认分片
        long sharding = value % 2;
        String key = logicTableName.concat("_").concat(String.valueOf(sharding));
        if (availableTargetNames.contains(key)){
            return key;
        }

        throw new UnsupportedOperationException("未找到分片");
    }
}
