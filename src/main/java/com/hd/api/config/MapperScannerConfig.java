package com.hd.api.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * mybatis mapper 扫描配置
 */
@Configuration
@AutoConfigureAfter(MybatisSpringConfig.class) //保证在MyBatisConfig实例化之后再实例化该类
public class MapperScannerConfig {

    /**
     * mapper接口的扫描器
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hd.api.dao");
        mapperScannerConfigurer.setMarkerInterface(Mapper.class);
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.hd.api.baseDao.BaseMapper");
        properties.setProperty("style", "normal");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }


}
