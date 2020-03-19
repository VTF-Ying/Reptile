package com.reptile.util;

import com.reptile.entity.Book;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashSet;
/**
 * @ProjectName: Reptile
 * @ClassName: BookUtil
 * @Description: TODO(s)
 * @Author: VTF
 * @create: 2020-03-16 10:19
 */
public class BookUtil {

    /**
     * 对新增书籍的详情进行拼接   不包括更新时间和更新内容
     * @param document
     * @param <T>
     * @return
     */

    public static <T> HashSet<Book> bookData(Document document){

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
            books.add(book);
        }

        return books;
    }


}
