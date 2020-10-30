package com.mola.bi.ds;

/**
 * 数据源信息存储
 *
 * @author hatim
 */
public class DataSourceHolder {
    /**
     * dataSources 线程变量
     */
    private static final ThreadLocal<String> ds = new ThreadLocal<>();

    public static void setDataSource(String name) {
        ds.set(name);
    }

    public static String getDataSource() {
        return ds.get();
    }

    public static void clearDataSource() {
        ds.remove();
    }
}
