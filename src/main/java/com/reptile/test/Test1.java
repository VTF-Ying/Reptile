package com.reptile.test;

import com.reptile.util.StringUtils;

import  java.util.*;
/**
 * @ProjectName: reptile
 * @ClassName: Test1
 * @Description: TODO(判断一个字符串是不是纯数字)
 * @Author: VTF
 * @create: 2020-04-01 16:50
 */
public class Test1 {


    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add(null);
        list.add(null);
        list.add("");
        list.add("123456");

        System.out.println(list.isEmpty());

        for (String o : list) {
            if (StringUtils.isRealEmpty(o)){
                continue;
            }
            System.out.println(o);
        }
        if (list.size()==0){
            System.out.println("你好");
        }

        System.out.println(list);
//            list.add("你好");
//            list.add("dddddd");
//            list.add("aa31aaa");
//            list.add("cccc");
//            list.add("2222");
//            list.add("33333");
//            list.add("你d12ddd");
//            list.add("fffff");
//            list.add("ccccc");
//            list.add("eeeee");
//            list.add("zz65zzz");
//            list.add("666666");
//            list.add("56565");
//            list.add("kkkk48k");
//            list.add("nnnn");
//            list.add("llllll");
//        List<Long> list1 = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        System.out.println(list.size());
//        for (String o : list) {
//            System.out.println(o);
//            if (isNumeric(o)){
//                list1.add(Long.parseLong(o));
//            }else{
//                list2.add(o);
//            }
//        }
//        System.out.println("===================数字=======================");
//        System.out.println(list1.size());
//        for (Long l : list1) {
//            System.out.println(l);
//        }
//        System.out.println("===================字符=======================");
//        System.out.println(list2.size());
//        for (String s : list2) {
//            System.out.println(s);
//        }
    }


}
