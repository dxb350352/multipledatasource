package com.dxb.self.aop;

public enum DataSourceEnum {
    DS1("ds1"), DS2("ds2");
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private DataSourceEnum(String key) {
        this.key = key;
    }
}
