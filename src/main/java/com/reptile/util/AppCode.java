package com.reptile.util;

/**
 * @ProjectName: Reptile
 * @ClassName: AppCode
 * @Description: TODO(应用代码)
 * @Author: VTF
 * @create: 2020-03-13 16:35
 */
public interface AppCode {
    int getCode();
    void setCode(int code);

    String getMessage();
    void setMessage(String message);

    default boolean isSuccess() {
        return this.getCode() == 0;
    }
}
