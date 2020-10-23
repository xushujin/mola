package com.mola.bi.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 多数据源配置
 *
 * @author hatim
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "${spring.datasource.druid.mysql.name}", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    public DruidDataSource mysqlDs() {
        return new DruidDataSource();
    }

    @Bean(name = "${spring.datasource.druid.clickhouse.name}", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.clickhouse")
    public DruidDataSource clickHouseDs() {
        return new DruidDataSource();
    }
}
