package com.sim.enums;

/**
 * Created by hyj on 2018年08月13日
 */
public enum WebsiteEnum {
    BOOKBAO("书包网",001);

    private String wName;
    private Integer wCode;

    WebsiteEnum(String wName, Integer wCode) {
        this.wName = wName;
        this.wCode = wCode;
    }

    public String getwName() {
        return wName;
    }

    public Integer getwCode() {
        return wCode;
    }
}
