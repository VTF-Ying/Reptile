package com.reptile.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReptileOne {
    /**
     * 将URL的资源以HTML格式保存在指定文件
     * @param args
     */
    public static void main(String[] args) {


        String url_str = "https://www.x23us.com/html/78/78881/";
        String filepath = "d:/index.html";
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        CharSetNum charSetNum = new CharSetNum();

        //获取网页的编码格式
        String charset = charSetNum.getEncodingByMeta(url_str);

            InputStream htm_in = charSetNum.gethim(url);

            //将网页通过指定字符编码格式转换出来
            String htm_str = charSetNum.InputStream2String(htm_in,charset);

            //解析网页
            Document doc= Jsoup.parse(htm_str);

            //将网页保存在指定文件夹
            charSetNum.saveHtml(filepath,htm_str);

            //获取 首页更新的小说的URL和标题
            String label="h3";
            charSetNum.getUrlAndTitle(doc,label);


        String htm_book = charSetNum.InputStream2String(htm_in,charset);
       //     System.out.println(newest);

           // System.out.println(doc.getElementsByClass("L"));
    }


}
