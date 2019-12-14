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



@Service("serchBookService")
public class SerchBookService {

    @Autowired
    SerchBookUrlAndTitleMapper serchBookUrlAndTitleMapper;
    @Autowired
    BookChapterMapper bookChapterMapper;

    CharSetNum charSetNum = new CharSetNum();

    /**
     * 对顶点小说的首页进行爬取
     * 得到大致的URL 和准确的书名
     * @param url_str
     */
    public  int SerchBookUrlAndTitle(String url_str){
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
                System.out.println("           "+((HomePage) list.get(i)).getBookName());
            }

        }
            if(newlist.size()!=0){
            return serchBookUrlAndTitleMapper.saveUrlAndTitle(newlist);

        }else
            {
                return 0;
            }

    }

    public void getBookContent(String name){
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


        //获取 首页更新的小说的URL和标题
        String label="L";
        List list=charSetNum.getUrlAndTitle(doc,label);

        String author_str=doc.getElementsByTag("h3").html();
        String author=author_str.substring(author_str.indexOf("：")+1,author_str.indexOf("&"));

        HomePage homePage = serchBookUrlAndTitleMapper.serchByName(name);
                if (homePage!=null){
                    homePage.setBookAuthor(author);
                    Date date=new Date();
                    homePage.setUpTime(charSetNum.getNowDate(date));
                    if (serchBookUrlAndTitleMapper.serchByChapterName(homePage.getBookName())==null){
                        serchBookUrlAndTitleMapper.insertGetBook(homePage);
                    }
                    List<BookChapter> bookChapterlist=new ArrayList<BookChapter>();
                    List<BookChapter> lis2=new ArrayList<BookChapter>();
                    for (int i=0;i<list.size();i++){
                        HomePage homePageChapter= (HomePage) list.get(i);

                        BookChapter bookChapter=new BookChapter();
                        String ChapterUrl=url_str+homePageChapter.getBookUrl();
                        bookChapter.setBookChapterUrl(ChapterUrl);
                        bookChapter.setBookChapter(homePageChapter.getBookName());
                        bookChapter.setBookDetailsId(serchBookUrlAndTitleMapper.serchByChapterName(name).getBookId());
                        bookChapter.setUpTime(charSetNum.getNowDate(date));
                        bookChapterlist.add(bookChapter);
                    }
                    for (int i=0;i<bookChapterlist.size();i++){
                      Integer k =  bookChapterMapper.selectChapterAll(bookChapterlist.get(i).getBookChapterUrl());
                        if (k==null){
                                lis2.add(bookChapterlist.get(i));
                        }
                    }
                    if (lis2.size()!=0){
                        bookChapterMapper.saveBookChapter(lis2);
                    }
                }

    }

    public String claerAll(Long pwd){
        if (pwd==967170123){
            serchBookUrlAndTitleMapper.clearAll();

        }else{
            return "密码错误";
        }
        return "成功";
    }
}
