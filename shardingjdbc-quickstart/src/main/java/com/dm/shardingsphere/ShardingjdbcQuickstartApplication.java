package com.dm.shardingsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author dm
 * @className ShardingjdbcQuickstartApplication
 * @description 启动类
 * @date 2020/8/5
 * @since JDK1.8
 */
@MapperScan("com.dm.shardingsphere.mapper")
@SpringBootApplication
public class ShardingjdbcQuickstartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbcQuickstartApplication.class, args);
    }
}
