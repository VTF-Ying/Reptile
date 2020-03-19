package com.reptile.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @ProjectName: Reptile
 * @ClassName: GetImg
 * @Description: TODO(爬虫模板类)
 * @Author: VTF
 * @create: 2020-03-12 16:44
 */
public class GetBookNameAndUrl {

    //顶点小说网的首页
    static String url = "https://www.23wxc.com/";
    //顶点小说网完本小说的链接后缀
    static String endUrl = "/top/allvisit_15590/";

    /**
     * 获取顶点小说网的  最新更新的书籍的书名和URL地址
     * @param url
     */
    public static void GetUpdeteBookNameAndUrl(String url){
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
            //System.out.println(document);
            // 查找更新版块
            Elements selcetPlate = document.select("[class=update]");
            //System.out.println(selcetPlate);
            // 在 更新模块中 查找 li 标签
            Elements seectLI = selcetPlate.select("li");
            //System.out.println(seectLI);
            // 查找更新的书籍的名称
            Elements listBookName = seectLI.select("[class=ul1]");
            Elements selectBookName = listBookName.select("a");
            for (Element bookName : selectBookName) {
                System.out.println(bookName.text()+" "+bookName.attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void GetEndBookNameAndUrl(String endUrl){
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
            Elements allBook = document.select("[class=ultop]");
            System.out.println(document);
            Elements allBooks = allBook.select("a");
            for (Element book : allBooks) {
                if (!book.text().equals("更多...") && !(book.attr("href").equals("/top/allvisit_1/") || book.attr("href").equals("/top/postdate_1/")) ){
                    System.out.println(book.text()+" "+book.attr("href"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /**
         * 获取顶点小说网的  最新更新的书籍的书名和URL地址
         * @param url
         */
        GetUpdeteBookNameAndUrl(url);

        /**
         *
         */
        //GetEndBookNameAndUrl("https://www.23wxc.com/top/allvisit_15/");
    }
}
