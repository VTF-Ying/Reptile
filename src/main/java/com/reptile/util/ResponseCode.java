package com.reptile.util;

/**
 * @ProjectName: Reptile
 * @ClassName: model
 * @Description: TODO(处理结果响应代码)
 * @Author: VTF
 * @create: 2020-03-13 16:33
 */
public enum ResponseCode implements AppCode {
    RESOURCE_NOT_FOUND(404, "资源不存在"),
    UNKOWN_EXCEPTION(-1, "请稍后重试！"),
    SUCCESS(0, "OK"),
    INSERT_EXCEPTION(10, "数据新增失败！"),
    INSERT_BATCH_EXCEPTION(11, "数据新增失败！"),
    UPDATE_EXCEPTION(20, "数据更新失败！"),
    DELETE_EXCEPTION(30, "数据删除失败！"),
    DISABLE_EXCEPTION(31, "使数据无效失败！"),
    SELECT_ONE_EXCEPTION(40, "数据获取失败！"),
    SELECT_EXCEPTION(41, "数据获取失败！"),
    SELECT_PAGINATION_EXCEPTION(42, "数据获取失败！"),
    STRING_NOT_FOUND    (43,"没有找到字符串"),
    INVALID_SYSTEM_CLOCK(10101, "系统时间回调到当前时间之前的时间点，拒绝产生ID%d毫秒"),
    UNKOWN_WORKER_ID(10102, "无法获取IdWorker标识"),
    INVALID_WORKER_ID(10103, "无效IdWorker标识，%d > %d"),
    UNKOWN_ELASTICSEARCH_BEAN(43, "无效获取elasticsearch实现bean"),

    INPUT_VALUE_IS_NULL(10001,"输入值为空"),
    INPUT_VALUE_IS_ILLEGAL(10002,"非法输入");
    private int code;
    private String message;

    private ResponseCode(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    @Override
    public String toString() {
        return Integer.toString(getCode());
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the code
     */
    @Override
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    @Override
    public void setCode(int code) {
        this.code = code;
    }


}

