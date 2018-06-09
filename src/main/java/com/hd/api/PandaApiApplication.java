package com.hd.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@PropertySource(value = {"classpath:api.properties"}) //添加配置文件
@ComponentScan(basePackages = "com.hd.api") //自动扫描包
public class PandaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandaApiApplication.class, args);
    }

}
