package com.reptile.util;

import com.reptile.util.exception.ApplicationException;

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
     * 查找字符最后出现的位置
     * @param str
     * @return
     */
    public static int varLast(String str,String i){
        int lastIndex =str.lastIndexOf(i);
        if(lastIndex == - 1){
            throw new ApplicationException(ResponseCode.STRING_NOT_FOUND );
        }
        return lastIndex;
    }

    /**
     * 查找字符最先出现的位置
     * @param str
     * @return
     */
    public static int varFirst(String str,String i){
        int lastIndex =str.lastIndexOf(i);
        if(lastIndex == 1){
            throw new ApplicationException(ResponseCode.STRING_NOT_FOUND );
        }
        return lastIndex;
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

