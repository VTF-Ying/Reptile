package com.reptile.service;

import com.reptile.entity.BookChapter;
import com.reptile.entity.HomePage;
import com.reptile.mapper.BookChapterMapper;
import com.reptile.mapper.SerchBookUrlAndTitleMapper;
import com.reptile.util.CharSetNum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("serchBookService")
public class SerchBookService {

    @Autowired
    SerchBookUrlAndTitleMapper serchBookUrlAndTitleMapper;
    @Autowired
    BookChapterMapper bookChapterMapper;

    //按每3个一组分割
    private static final Integer MAX_NUMBER = 980;

    /**
     * 计算切分次数
     */
    public int countStep(int size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    CharSetNum charSetNum = new CharSetNum();

    /**
     * 对顶点小说的首页进行爬取
     * 得到大致的URL 和准确的书名
     * @param url_str
     */
    public  int SerchBookUrlAndTitle(HomePage homePage1){
       String url_str = homePage1.getBookUrl();
        String filepath = "d:/index.html";
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


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
        String label="poptext";
        List list=charSetNum.getUrlAndTitle(doc,label);
        List newlist=new ArrayList();
        System.out.println("新增书籍:\r");
        for (int i=0;i<list.size();i++){
            HomePage homePage= (HomePage) list.get(i);
            if(serchBookUrlAndTitleMapper.serchByName(homePage.getBookName())==null){
                newlist.add(list.get(i));
                System.out.println(i+1+"       "+((HomePage) list.get(i)).getBookName());
            }

        }
            if(newlist.size()!=0){
            return serchBookUrlAndTitleMapper.saveUrlAndTitle(newlist);

        }else
            {
                return 0;
            }

    }

    public void getBookContent(HomePage homePage){
        String name = homePage.getBookName();
        String url_str=serchBookUrlAndTitleMapper.serchByName(name).getBookUrl();
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //获取网页的编码格式
        String charset = charSetNum.getEncodingByMeta(url_str);

        InputStream htm_in = charSetNum.gethim(url);

        //将网页通过指定字符编码格式转换出来
        String htm_str = charSetNum.InputStream2String(htm_in,charset);

        //解析网页
        Document doc= Jsoup.parse(htm_str);
        String filepath="d:/book/"+name+".html";
        File file=new File(filepath);
        charSetNum.saveHtml(filepath,htm_str);


        //获取 小说的URL和标题
        String label="hst";
        List list3=charSetNum.getUrlByName(doc,label);
        String author_str=doc.getElementsByTag("td").get(1).html();
        String author=author_str.substring(6);
        for (int i=0;i<list3.size();i++) {
            homePage = (HomePage) list3.get(i);
        }
                if (homePage!=null) {
                    homePage.setBookAuthor(author);
                    homePage.setBookUrl(homePage.getBookUrl());
                    Date date = new Date();
                    homePage.setUpTime(charSetNum.getNowDate(date));
                    if (serchBookUrlAndTitleMapper.serchByChapterName(homePage.getBookName()) == null) {
                        serchBookUrlAndTitleMapper.insertGetBook(homePage);
                    }
                    List<BookChapter> list = charSetNum.getContextByBookName(homePage);
                    List<BookChapter> newlist = new ArrayList();
                    int k = serchBookUrlAndTitleMapper.serchByChapterName(homePage.getBookName()).getBookId();
                    for (int i = 0; i < list.size(); i++) {
                        BookChapter bookChapter = bookChapterMapper.selectChapterAll(list.get(i).getBookChapterUrl());
                        if (bookChapter == null || !(bookChapter.getBookChapter().equals(list.get(i).getBookChapter()))) {
                            list.get(i).setBookDetailsId(k);
                            newlist.add(list.get(i));
                        }
                    }
                    int limit = countStep(newlist.size());
                    //方法一：使用流遍历操作
                    List<List<BookChapter>> mglist = new ArrayList<>();
                    Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
                        mglist.add(newlist.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
                    });

                    System.out.println(mglist);

                    //方法二：获取分割后的集合
                    List<List<BookChapter>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> newlist.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());

                    System.out.println(splitList);
                    if (splitList.size()>0){
                        for (int i=0;i<splitList.size();i++){
                            bookChapterMapper.saveBookChapter(splitList.get(i));
                        }
                    }
                    saveContent(k);
                }
            }


    public String claerAll(){

        serchBookUrlAndTitleMapper.clearIndex();
        serchBookUrlAndTitleMapper.clearDetails();
        serchBookUrlAndTitleMapper.clearTransition();
        return "ok";
    }

    public void saveContent(Integer id){
        List<BookChapter> bookChapterList=bookChapterMapper.selectChapterAllById(id);
        for (int i=0;i<bookChapterList.size();i++){
           String url_str=bookChapterList.get(i).getBookChapterUrl();
           URL url = null;
           try {
               url = new URL(url_str);
           } catch (MalformedURLException e) {
               e.printStackTrace();
           }
           //获取网页的编码格式
           String charset = charSetNum.getEncodingByMeta(url_str);
           InputStream htm_in = charSetNum.gethim(url);
           //将网页通过指定字符编码格式转换出来
           String htm_str = charSetNum.InputStream2String(htm_in,charset);
           //解析网页
           Document doc= Jsoup.parse(htm_str);
           String context=doc.getElementById("contents").html();
            System.out.println(context);
       }
    }
}
