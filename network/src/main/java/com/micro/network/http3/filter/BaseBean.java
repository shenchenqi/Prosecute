package com.micro.network.http3.filter;

import java.io.Serializable;

/**
 * created by kilin on 20-3-19 上午9:49
 */
public class BaseBean<T> implements Serializable {
    private int code;
    private T data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}