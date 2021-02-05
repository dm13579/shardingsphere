package com.dm.shardingsphere.algorithem;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dm
 * @version 1.0
 * @className TableStrategyStandardRangeAlgorithm
 * @description 多分片键复杂分片算法类名
 * @date 2021/2/5 15:27
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
public class TableStrategyHintAlgorithm implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        System.out.println("TableStrategyHintAlgorithm:=====================================================");
        // 数据节点
        System.out.println("availableTargetNames：" + String.join(";", availableTargetNames));
        String logicTableName = shardingValue.getLogicTableName();
        String columnName = shardingValue.getColumnName();
        Collection<Long> values = shardingValue.getValues();

        if (CollectionUtils.isEmpty(values)){
            return availableTargetNames;
        }
        return values.stream().map(value -> logicTableName.concat("_").concat(String.valueOf(value))).collect(Collectors.toList());
    }
}
