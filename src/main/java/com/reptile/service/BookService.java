package com.reptile.service;

import com.reptile.entity.Book;
import com.reptile.util.ResponseCode;
import com.reptile.util.StringUtils;
import com.reptile.util.exception.ApplicationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: Reptile
 * @ClassName: BookSrrvice
 * @Description: TODO(书籍业务处理类)
 * @Author: VTF
 * @create: 2020-03-13 16:48
 */
@Service
public class BookService {

    public Set<Book> getUpdateBook(String url) {
        if (!StringUtils.isRealEmpty(null)){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_NULL);
        }
        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1;Trident/5.0)").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements selcetPlate = document.select("[class=update]");
        Elements seectLI = selcetPlate.select("li");
        Elements listBookName = seectLI.select("[class=ul1]");
        Elements selectBookName = listBookName.select("a");
        HashSet<Book> books = new HashSet<Book>();
        for (Element bookName : selectBookName) {
            Book book = new Book();
            book.setBookName(bookName.text());
            book.setBookUrl(bookName.attr("href"));
            books.add(book);
        }
        return books;
    }

}
