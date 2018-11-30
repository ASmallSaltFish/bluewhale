package com.qs.bluewhale.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描mapper配置
 */
@Configuration
//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，二者配置一个即可
@MapperScan(basePackages = {"com.qs.bluewhale.mapper"})
public class MapperScanConfig {

    /**
     * 相当于顶部的：@MapperScan("com.qs.blueWhale.mapper*")
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//        scannerConfigurer.setBasePackage("com.qs.blueWhale.mapper*");
//        return scannerConfigurer;
//    }

    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
