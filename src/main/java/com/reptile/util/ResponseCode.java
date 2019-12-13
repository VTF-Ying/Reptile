package com.reptile.util;

import com.reptile.entity.AppCode;

public enum ResponseCode implements AppCode {
    RESOURCE_NOT_FOUND(404, "资源不存在"),
    UNKOWN_EXCEPTION(-1, "系统压力山大,请稍后重试！"),
    SUCCESS(0, "OK"),
    INSERT_EXCEPTION(10, "数据新增失败！"),
    INSERT_BATCH_EXCEPTION(11, "数据新增失败！"),
    UPDATE_EXCEPTION(20, "数据更新失败！"),
    DELETE_EXCEPTION(30, "数据删除失败！"),
    DISABLE_EXCEPTION(31, "使数据无效失败！"),
    SELECT_ONE_EXCEPTION(40, "数据获取失败！"),
    SELECT_EXCEPTION(41, "数据获取失败！"),
    SELECT_PAGINATION_EXCEPTION(42, "数据获取失败！");


    private int code;
    private String message;

    private ResponseCode(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    @Override
    public String toString() {
        return Integer.toString(this.getCode());
    }
    @Override
    public String getMessage() {
        return this.message;
    }
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public int getCode() {
        return this.code;
    }
    @Override
    public void setCode(int code) {
        this.code = code;
    }
}

