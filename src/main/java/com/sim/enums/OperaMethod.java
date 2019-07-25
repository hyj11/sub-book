package com.sim.enums;

/**
 * Created with IntelliJ IDEA.
 * User: huangyj
 * operation: 操作枚举
 * Date: 2019/7/24
 */
public enum  OperaMethod {
    DOWN_BOOK(1,"下载书籍");

    private Integer oid;
    private String operate;

    OperaMethod(Integer oid,String operate) {
        this.operate = operate;
        this.oid = oid;
    }

    public String getOperate() {
        return operate;
    }

    public Integer getOid() {
        return oid;
    }
}
