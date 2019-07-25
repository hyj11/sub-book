package com.sim.exception;

/**
 * @author huangyujiao on 2018/1/24.
 * @version 1.0
 */
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
