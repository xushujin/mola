package com.mola.core.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.jdbc.metadata.AbstractDataSourcePoolMetadata;

/**
 * DruidDataSourcePoolMetadata
 *
 * @author hatim
 */
public class DruidDataSourcePoolMetadata extends AbstractDataSourcePoolMetadata<DruidDataSource> {
    /**
     * Create an instance with the data source to use.
     *
     * @param dataSource the data source
     */
    public DruidDataSourcePoolMetadata(DruidDataSource dataSource) {
        super(dataSource);
    }

    /**
     * Return the current number of active connections that have been allocated from the
     * data source or {@code null} if that information is not available.
     *
     * @return the number of active connections or {@code null}
     */
    @Override
    public Integer getActive() {
        return getDataSource().getActiveCount();
    }

    /**
     * Return the maximum number of active connections that can be allocated at the same
     * time or {@code -1} if there is no limit. Can also return {@code null} if that
     * information is not available.
     *
     * @return the maximum number of active connections or {@code null}
     */
    @Override
    public Integer getMax() {
        return getDataSource().getMaxActive();
    }

    /**
     * Return the minimum number of idle connections in the pool or {@code null} if that
     * information is not available.
     *
     * @return the minimum number of active connections or {@code null}
     */
    @Override
    public Integer getMin() {
        return getDataSource().getMinIdle();
    }

    /**
     * Return the query to use to validate that a connection is valid or {@code null} if
     * that information is not available.
     *
     * @return the validation query or {@code null}
     */
    @Override
    public String getValidationQuery() {
        return getDataSource().getValidationQuery();
    }

    /**
     * The default auto-commit state of connections created by this pool. If not set
     * ({@code null}), default is JDBC driver default (If set to null then the
     * java.sql.Connection.setAutoCommit(boolean) method will not be called.)
     *
     * @return the default auto-commit state or {@code null}
     */
    @Override
    public Boolean getDefaultAutoCommit() {
        return getDataSource().isDefaultAutoCommit();
    }

}
