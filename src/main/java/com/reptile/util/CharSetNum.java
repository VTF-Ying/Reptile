package com.reptile.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
public class CharSetNum {

    /**
         * 从meta中获取页面编码
         * @param strUrl
         * @return
         * */

        public  String getEncodingByMeta(String strUrl){
            String charset = null;
            try {
                URLConnection urlConn = new URL(strUrl).openConnection();
                //避免被拒绝
                urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
                // 将html读取成行,放入list
                List<String> lines = IOUtils.readLines(urlConn.getInputStream());
                for (String line : lines) {
                    if(line.contains("http-equiv") && line.contains("charset")){
                        //                    System.out.println(line);
                        String tmp = line.split(";")[1];
                        charset = tmp.substring(tmp.indexOf("=")+1, tmp.indexOf("\""));
                    }else{
                        continue;
                    }
                }
                return charset;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return charset;
            } catch (IOException e) {
                e.printStackTrace();
                return charset;
            }
        }
    }


