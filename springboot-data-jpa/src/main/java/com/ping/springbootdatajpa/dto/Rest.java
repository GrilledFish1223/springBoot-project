package com.ping.springbootdatajpa.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshengping
 */
public class Rest<T> {

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_SERVICE_ERROR = 100;
    public static final int STATUS_NOT_LOGIN = 96;

    private int status;
    private int errorCode;
    private String errorMessage;
    private T data = null;

    public Rest() {
        this.setStatus(STATUS_SUCCESS);
    }

    public Rest status(int status) {
        this.setStatus(status);
        return this;
    }

    public Rest errorCode(int errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

    public Rest errorMessage(String message) {
        this.setErrorMessage(message);
        return this;
    }

    public Rest<T> data(T data) {
        this.data = data;
        return this;
    }

    public static Rest build() {
        return new Rest();
    }

    public static Rest<Map<String, Object>> build(String name, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(name, data);
        return new Rest<Map<String, Object>>().data(map);
    }

    public static Rest error() {
        return new Rest().status(STATUS_SERVICE_ERROR).errorCode(1000);
    }

    public static Rest error(int code, String message) {
        return new Rest().status(STATUS_SERVICE_ERROR).errorCode(code).errorMessage(message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (mapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
