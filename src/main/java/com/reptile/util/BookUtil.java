package com.reptile.util;

import com.reptile.entity.Book;
import com.reptile.util.exception.ApplicationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
/**
 * @ProjectName: Reptile
 * @ClassName: BookUtil
 * @Description: TODO(书籍详情)
 * @Author: VTF
 * @create: 2020-03-16 10:19
 */
public class BookUtil {

    /**
     * 对新增书籍的详情进行拼接
     * @param url
     * @param <T>
     * @return
     */

    public static <T> HashSet<Book> bookData(String url){
        if (StringUtils.isRealEmpty(url)){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_NULL);
        }
        Document document = null;
        Document document1 = null;
        try {
            document = Jsoup.connect(url).timeout(5000).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
        } catch (IOException e) {
            throw new ApplicationException(ResponseCode.UNKOWN_EXCEPTION);
        }
        Elements selcetPlate = document.select("[class=update]");
        Elements selcetLi = selcetPlate.select("li");

        HashSet<Book> books = new HashSet<Book>();
        for (int i = 0; i <30 ; i++) {
            Book book = new Book();
            String str = (selcetLi.get(i).select("p")).select("[class=ul1]").text();
            String str1 = selcetLi.get(i).select("a").attr("title").toString();
            book.setBookId(GeneratIdUtil.getGeneratID());
            book.setBookType(str.substring(0,StringUtils.varLast(str,"]")+1));
            book.setBookName(str1.substring(0,StringUtils.varLast(selcetLi.get(i).select("[class=ul1]").select("a").attr("title"),"txt")));
            book.setBookUrl(selcetLi.get(i).select("[class=ul1]").select("a").attr("href"));
            book.setBookUpdateDate(GeneratIdUtil.getDate("yyyyMMddhhmmss"));
            book.setBookAuthor(selcetLi.get(i).select("p:nth-child(3)").text());
            try {
                document1=Jsoup.connect(selcetLi.get(i).select("[class=ul1]").select("a").attr("href")).timeout(5000).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            books.add(book);
        }

        return books;
    }


}
