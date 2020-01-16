package com.reptile.util;

import com.reptile.entity.BookChapter;
import com.reptile.entity.HomePage;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public List<HomePage> getUrlByName(Document doc,String label){
        /**
         * 获取的URL和书名
         */
        Elements newest= doc.getElementsByClass(label);           //dom元素   id选择
        List<HomePage> homePagelist=new ArrayList<HomePage>();
        for(int i=0;i<newest.size();i++){
            String a=newest.get(i).toString();

            String Url="(?<=href=\").*?(?=\")";
            String st=",(?:[^\"\\\\]|\\\\.)*";

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
                homePage.setBookName(smatcher.group().substring(1,(smatcher.group().length()-4)));
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

    /**
     * 文件尾部追加内容
     * @param filepath
     * @param content
     */
    public  void method1(String filepath , String content) {
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f=new File(filepath);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对
     */
    public List<BookChapter> getContextByBookName(HomePage homePage) {
        String url_str = homePage.getBookUrl();
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //获取网页的编码格式
        String charset = getEncodingByMeta(url_str);
        InputStream htm_in = gethim(url);
        //将网页通过指定字符编码格式转换出来
        String htm_str = InputStream2String(htm_in, charset);
        //解析网页
        Document doc = Jsoup.parse(htm_str);
        //获取 小说的URL和标题
        String label = "L";
        List<HomePage> list = getUrlAndTitle(doc, label);
        List<BookChapter> list1=new ArrayList<BookChapter>();
        for (int i=0;i<list.size();i++){
            BookChapter bookChapter = new BookChapter();
            bookChapter.setBookDetailsId(homePage.getBookId());
            bookChapter.setBookChapter(list.get(i).getBookName());
            bookChapter.setBookChapterUrl(homePage.getBookUrl()+list.get(i).getBookUrl());
            Date date = new Date();
            bookChapter.setUpTime(getNowDate(date));
            list1.add(bookChapter);
        }
        return list1;
    }
}


