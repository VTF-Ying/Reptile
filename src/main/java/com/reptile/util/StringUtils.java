package com.reptile.util;

/**
 * @ProjectName: Reptile
 * @ClassName: StringUtils
 * @Description: TODO(字符串处理工具类)
 * @Author: VTF
 * @create: 2020-03-13 16:49
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * 不去除首尾空格
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为空
     * 去除首尾空格
     * @param s
     * @return
     */
    public static boolean isRealEmpty(String s) {
        boolean result = isEmpty(s);
        if (!result) {
            result = s.trim().length() == 0;
        }

        return result;
    }

    /**
     * 判断字符串是否为空
     * 去除首尾空格
     * @param s
     * @return
     */
    public static boolean isTrimedEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
    /**
     * 判断字符串是否非空
     * 不去除首尾空格
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * 判断字符串是否非空
     * 去除首尾空格
     * @param s
     * @return
     */
    public static boolean isNotRealEmpty(String s) {
        return !isRealEmpty(s);
    }

    public static String defaultString(final String str, final String defaultStr) {
        return isTrimedEmpty(str) ? defaultStr : str.trim();
    }
}

