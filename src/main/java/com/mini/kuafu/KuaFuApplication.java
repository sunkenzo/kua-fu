package com.mini.kuafu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author kenzo
 * @create 2021-02-04 17:50
 */
@SpringBootApplication
@EnableRetry
@MapperScan("com.mini.kuafu.service.**.dao")
public class KuaFuApplication {
    public static void main(String[] args) {
        SpringApplication.run(KuaFuApplication.class, args);
    }
}
