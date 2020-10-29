package com.mola.bi.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hatim
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    String name() default TargetDataSource.CLICKHOUSE_DS;

    public static String CLICKHOUSE_DS = "clickHouseDs";

    public static String MYSQL_DS = "mysqlDs";
}
