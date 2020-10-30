package com.mola.bi.ds;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 *
 * @author hatim
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    public DataSource mysqlDs() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.clickhouse")
    public DataSource clickHouseDs() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource mysqlDs, DataSource clickHouseDs) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(TargetDs.MYSQL_DS, mysqlDs);
        targetDataSources.put(TargetDs.CLICK_HOUSE_DS, clickHouseDs);
        return new DynamicDataSource(mysqlDs, targetDataSources);
    }
}
