package com.sim.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class ResponseObj<T> implements Serializable {
    private static final long serialVersionUID = 7643344781721370931L;
    public static final String CODE_SUCCESS = "000000";
    public static final String CODE_FAILED = "999999";
    private String retCode;
    private String retSubCode;
    private String retMsg;
    private String retType;
    private T retData;
    private String bNo;
    private String bFlag;

    public ResponseObj() {
    }

    public ResponseObj(String retCode, String retMsg, T retData) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retData = retData;
    }

    public ResponseObj(T retData) {
        this.retCode = "000000";
        this.retMsg = "";
        this.retData = retData;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetSubCode() {
        return this.retSubCode;
    }

    public void setRetSubCode(String retSubCode) {
        this.retSubCode = retSubCode;
    }

    public String getRetMsg() {
        return this.retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetType() {
        return this.retType;
    }

    public void setRetType(String retType) {
        this.retType = retType;
    }

    public T getRetData() {
        return this.retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }

    public String getbNo() {
        return this.bNo;
    }

    public void setbNo(String bNo) {
        this.bNo = bNo;
    }

    public String getbFlag() {
        return this.bFlag;
    }

    public void setbFlag(String bFlag) {
        this.bFlag = bFlag;
    }

    public static ResponseObj success() {
        return new ResponseObj("000000", (String) null, (Object) null);
    }

    public static ResponseObj success(String retMsg) {
        return new ResponseObj("000000", retMsg, (Object) null);
    }

    public ResponseObj success(String retMsg, T retData) {
        return new ResponseObj("000000", retMsg, retData);
    }

    public static ResponseObj failed() {
        return new ResponseObj("999999", (String) null, (Object) null);
    }

    public static ResponseObj failed(String retMsg) {
        return new ResponseObj("999999", retMsg, (Object) null);
    }

    public static ResponseObj failed(String retCode, String retMsg) {
        return new ResponseObj(retCode, retMsg, (Object) null);
    }

    public boolean isSuccess() {
        return this.getRetCode() == null ? false : this.getRetCode().equals("000000");
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}


