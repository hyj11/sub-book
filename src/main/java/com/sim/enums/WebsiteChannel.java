package com.sim.enums;

/**
 * Created by hyj on 2018年08月13日
 */
public enum WebsiteChannel {
    BOOKBAO("书包网",001);

    private String wName;
    private Integer wCode;

    WebsiteChannel(String wName, Integer wCode) {
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
