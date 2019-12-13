package com.reptile.entity;

public interface AppCode {
    int getCode();

    void setCode(int var1);

    String getMessage();

    void setMessage(String var1);

    default boolean isSuccess() {
        return this.getCode() == 0;
    }
}