package com.mola.bi.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;


/**
 * @author hatim
 */
@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

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

    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("mysqlDs", mysqlDs());
        targetDataSources.put("clickHouseDs", clickHouseDs());
        setTargetDataSources(targetDataSources);
        setDefaultTargetDataSource(mysqlDs());
    }
}
