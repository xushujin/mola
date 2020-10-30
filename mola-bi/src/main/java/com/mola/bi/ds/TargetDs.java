package com.mola.bi.ds;

import java.lang.annotation.*;

/**
 * @author hatim
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDs {
    String CLICK_HOUSE_DS = "clickHouseDs";
    String MYSQL_DS = "mysqlDs";

    String value() default MYSQL_DS;
}
