package com.reptile.util;

import com.reptile.entity.Book;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.*;
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
        Elements selcetP = selcetPlate.select("p");

        List<List<Element>> bookStr= ListUtil.splitToPieces(selcetP,3);
        HashSet<Book> books = new HashSet<Book>();

        for (List<Element> elements : bookStr) {
            Book book = new Book();

            for (int i = 0; i <elements.size() ; i++) {
                StringBuffer Str = new StringBuffer();
                if (i==0){
                    String bookType = (Str.append(elements.get(i))).substring(15,21);
                    book.setBookType(bookType);
                    String bookNameStr = Str.append(elements.get(i)).substring(0,(Str.append(elements.get(i))).length()-9);
                    int j = StringUtils.varLast(bookNameStr,">");
                    String bookName = bookNameStr.substring(j+1,bookNameStr.length());
                    book.setBookName(bookName);
                    int n = StringUtils.varLast(bookNameStr,"https");
                    int m = StringUtils.varLast(bookNameStr,"title");
                    String bookUrl = bookNameStr.substring(n,m-2);
                    book.setBookUrl(bookUrl);
                }
                if(i==1){
                    continue;
                }
                if (i==2){
                    String string = (Str.append(elements.get(i))).substring(3);
                    String bookAuthor = string.substring(0,string.length()-4);
                    book.setBookAuthor(bookAuthor);
                }
            }
            book.setBookId(GeneratIdUtil.getGeneratID());
            books.add(book);
        }
        return books;
    }


}
