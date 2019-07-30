package com.dxb.self.aop;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> dataSourceContext = new InheritableThreadLocal<>();

    public static void setDataSource(String ds) {
        dataSourceContext.set(ds);
    }

    public static String getDataSource() {
        return dataSourceContext.get();
    }

    public static void clear() {
        dataSourceContext.remove();
    }
}
