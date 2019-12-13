package com.reptile.util;

import com.reptile.entity.AppCode;

public class ResponseData<T> {
    private int code;
    private String msg;
    private T data;
    private String traceId;

    public ResponseData() {
        this(ResponseCode.UNKOWN_EXCEPTION);
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public ResponseData(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public ResponseData(AppCode appCode) {
        this.code = appCode.getCode();
        this.msg = appCode.getMessage();
    }

    public ResponseData(AppCode appCode, T data) {
        this.code = appCode.getCode();
        this.msg = appCode.getMessage();
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public T getData() {
        return this.data;
    }

    public ResponseData setData(T data) {
        this.data = data;
        return this;
    }

    public void responseCode(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public void ok() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMessage();
    }


}
