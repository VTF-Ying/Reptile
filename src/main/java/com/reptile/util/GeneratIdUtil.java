package com.reptile.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: Reptile
 * @ClassName: GeneratIdUtil
 * @Description: TODO(一句话描述该类的功能)
 * @Author: VTF
 * @create: 2020-03-16 10:01
 */
public class GeneratIdUtil {
    /**
     * 根据传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat
     *            yyyyMMddhhmmss
     * @return
     */
    public static String getDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getRandomNum(int num){
        String numStr = "";
        for(int i = 0; i < num; i++){
            numStr += (int)(10*(Math.random()));
        }
        return numStr;
    }
    /**
     * 生成id
     * @return
     */
    public static Long getGeneratID(){
        String sformat = "yyyyMMdd";
        int num = 9;
        String idStr = getDate(sformat) + getRandomNum(num);
        Long id = Long.valueOf(idStr);
        return id;
    }

    public static Long getGeneratID(String str){
        String sformat = "MMdd";
        int num = 5;
        String idStr = (str.substring(0,str.length()-1)).substring(StringUtils.varLast(str.substring(0,str.length()-3),"/")+1,(str.substring(0,str.length()-1)).length())+getDate(sformat) + getRandomNum(num);
        Long id = Long.valueOf(idStr);
        return id;
    }

}
