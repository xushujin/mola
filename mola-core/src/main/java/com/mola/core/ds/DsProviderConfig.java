package com.mola.core.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.jdbc.DataSourceUnwrapper;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源健康检查
 *
 * @author hatim
 */
@Configuration(proxyBeanMethods = false)
public class DsProviderConfig {
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(DruidDataSource.class)
    static class DruidDataSourceMetadataProviderConfiguration {
        @Bean
        DataSourcePoolMetadataProvider druidDataSourceMetadataProviderConfiguration() {
            return (dataSource) -> {
                DruidDataSource druidDataSource = DataSourceUnwrapper.unwrap(dataSource, DruidDataSource.class);
                if (druidDataSource != null) {
                    return new DruidDataSourcePoolMetadata(druidDataSource);
                }
                return null;
            };
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(ShardingDataSource.class)
    static class ShardingDataSourceMetadataProviderConfiguration {
        @Bean
        DataSourcePoolMetadataProvider shardingDataSourceMetadataProvider() {
            return (dataSource) -> {
                ShardingDataSource shardingDataSource = DataSourceUnwrapper.unwrap(dataSource, ShardingDataSource.class);
                if (shardingDataSource != null) {
                    return new ShardingDataSourcePoolMetadata(shardingDataSource);
                }
                return null;
            };
        }
    }
}
