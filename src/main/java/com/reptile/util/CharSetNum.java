package com.reptile.util;

import com.reptile.entity.HomePage;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * saveHtml
     * 将字符串保存到文件
     * @param filepath
     * 需要保存的文件路径
     * @param str
     * string saved
     */
    public  void saveHtml(String filepath, String str){

        try {
      /*@SuppressWarnings("resource")
      FileWriter fw = new FileWriter(filepath);
      fw.write(str);
      fw.flush();*/
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "utf-8");
            outs.write(str);
            System.out.print(str);
            outs.close();
        } catch (IOException e) {
            System.out.println("Error at save html...");
            e.printStackTrace();
        }
    }

    public InputStream gethim(URL url) {
        int sec_cont = 1000;
        URLConnection url_con = null;
        try {
            url_con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        url_con.setDoOutput(true);
        url_con.setReadTimeout(10 * sec_cont);
        url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        InputStream htm_in = null;
        try {
            htm_in = url_con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htm_in;
     }

    /**
     * @param in_st
     * 需要转换的输入流
     * @param charset
     * @throws IOException
     * if an error occurred
     */
    public  String InputStream2String(InputStream in_st,String charset){
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new InputStreamReader(in_st, charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuffer res = new StringBuffer();
        String line = "";
        while(true){
            try {
                if (!((line = buff.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            res.append(line+"\r");
        }
        return res.toString();
    }

    public List<HomePage> getUrlAndTitle(Document doc,String label){
        /**
         * 获取的URL和书名
         */
        Elements newest= doc.getElementsByClass(label);           //dom元素   id选择
        List<HomePage> homePagelist=new ArrayList<HomePage>();
        for(int i=0;i<newest.size();i++){
            String a=newest.get(i).toString();

            String Url="(?<=href=\").*?(?=\")";
            String st="(?<=>)[^<>]+(?=<)";

            //1.将正在表达式封装成对象Patten 类来实现
            Pattern pattern = Pattern.compile(Url);
            Pattern spattern = Pattern.compile(st);
            //2.将字符串和正则表达式相关联
            Matcher matcher = pattern.matcher(a);
            Matcher smatcher = spattern.matcher(a);
            //3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。

            //System.out.println(matcher.matches());
            //查找符合规则的子串

            while(matcher.find() && smatcher.find()){
                //获取 字符串
                System.out.println(matcher.group()+" -- "+smatcher.group());
                //获取的字符串的首位置和末位置
                // System.out.println(matcher.start()+"--"+matcher.end());
                HomePage homePage=new HomePage();
                homePage.setBookUrl(matcher.group());
                homePage.setBookName(smatcher.group());
                Date date=new Date();
                String time=getNowDate(date);
                homePage.setUpTime(time);
                homePagelist.add(homePage);
            }
        }
        return homePagelist;
    }

    public  String getNowDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

}


