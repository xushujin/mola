package com.mola.bi.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;


/**
 * @author hatim
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }


}
