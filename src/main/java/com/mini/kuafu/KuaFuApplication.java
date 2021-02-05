package com.mini.kuafu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kenzo
 * @create 2021-02-04 17:50
 */
@SpringBootApplication
@MapperScan("com.mini.kuafu.service.**.dao")
public class KuaFuApplication {
    public static void main(String[] args) {
        SpringApplication.run(KuaFuApplication.class, args);
    }
}
