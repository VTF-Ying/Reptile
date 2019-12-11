package com.reptile.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReptileOne {
    /**
     * 将URL的资源以HTML格式保存在指定文件
     * @param args
     */
    public static void main(String[] args) {


        String url_str = "https://www.x23us.com/";
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
            charSetNum.getUrlAndTitle(doc);


        String htm_book = charSetNum.InputStream2String(htm_in,charset);
       //     System.out.println(newest);

           // System.out.println(doc.getElementsByClass("L"));
    }


}
