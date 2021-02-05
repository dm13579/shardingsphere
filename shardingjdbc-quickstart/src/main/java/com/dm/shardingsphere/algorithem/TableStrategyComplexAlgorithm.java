package com.dm.shardingsphere.algorithem;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dm
 * @version 1.0
 * @className TableStrategyStandardRangeAlgorithm
 * @description 多分片键复杂分片算法类名
 * @date 2021/2/5 15:27
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
public class TableStrategyComplexAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        System.out.println("TableStrategyComplexAlgorithm:=====================================================");
        // 数据节点
        System.out.println("availableTargetNames：" + String.join(";", availableTargetNames));
        // 分片键Map,key-键名如order_id,value-键名对应的值如1231131231414
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        // 这里返回的是个列表所以in操作也是可以的
        Collection<Long> orderIds = columnNameAndShardingValuesMap.get("order_id");
        Collection<Long> userIds = columnNameAndShardingValuesMap.get("user_id");
//        System.out.println("orderIds：" + orderIds.stream().map(String::valueOf).collect(Collectors.joining(";")) +
//                ";userIds：" + userIds.stream().map(String::valueOf).collect(Collectors.joining(";")));

        // 范围查询,key-键名如order_id,value-Range类型,key对应范围
        Map<String, Range<Long>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        Range<Long> orderIdRange = columnNameAndRangeValuesMap.get("order_id");
        Range<Long> userIdRange = columnNameAndRangeValuesMap.get("user_id");

        // 分片
        List<String> shardList = new ArrayList<>();
        if(CollectionUtils.isEmpty(orderIds)){
            return availableTargetNames;
        }
        for(Long orderId: orderIds){
            long sharding = orderId % 2;

            shardList.add(shardingValue.getLogicTableName().concat("_").concat(String.valueOf(sharding)));
        }
        return shardList;
    }
}
