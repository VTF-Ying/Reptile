package com.reptile.service;

import com.reptile.entity.Book;
import com.reptile.entity.BookData;
import com.reptile.mapper.BookDataMapper;
import com.reptile.mapper.BookMapper;
import com.reptile.util.BookUtil;
import com.reptile.util.GeneratIdUtil;
import com.reptile.util.ResponseCode;
import com.reptile.util.StringUtils;
import com.reptile.util.exception.ApplicationException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookDataMapper bookDataMapper;

    Logger logger = LoggerFactory.getLogger(BookService.class);

    /**
     * @Author VTF
     * @Description //获取更新的书籍列表
     * @Param
     **/
    public Set<Book> getUpdateBook(String url) {

        Elements selcetLi = BookUtil.bookData(url);
        HashSet<Book> listBooks = new HashSet<>();
        for (int i = 0; i <30 ; i++) {
            Book book = new Book();
            String str = (selcetLi.get(i).select("p")).select("[class=ul1]").text();
            String str1 = selcetLi.get(i).select("a").attr("title").toString();
            book.setBookName(str1.substring(0, StringUtils.varLast(selcetLi.get(i).select("[class=ul1]").select("a").attr("title"),"txt")));
            Book book1 = bookMapper.getBookByNameOrId(book);
            if (book1 != null){
                continue;
            }
            book.setBookId(GeneratIdUtil.getGeneratID());
            book.setBookType(str.substring(0,StringUtils.varLast(str,"]")+1));
            book.setBookUrl(selcetLi.get(i).select("[class=ul1]").select("a").attr("href"));
            book.setBookUpdateDate(GeneratIdUtil.getDate("yyyy-MM-dd"));
            book.setBookAuthor(selcetLi.get(i).select("p:nth-child(3)").text());
            book.setBookIntroduction(BookUtil.bookIntroduction(book.getBookUrl()));
            listBooks.add(book);
        }

        if(listBooks.size()==0){
            return bookMapper.getBookByDate();
        }
        bookMapper.saveBooks(listBooks);
        logger.debug( new Date("yyyy-HH-mm-ss")+""+ (bookMapper.getBookByDate()).toString());
     return bookMapper.getBookByDate();
    }

    /**
     * @Author VTF
     * @Description //查询所有书籍
     * @Param 
     **/
    public List<Book> getAllBook(){
        return bookMapper.getAllBook();
    }

    /**
     * @Author VTF
     * @Description //根据书籍ID 或者书名 获取书的信息
     * @Param
     **/
    public Book getBookByNameOrId(Book book){
        if ((book.getBookId() == null || book.getBookId()==0) && (book.getBookName() == null || book.getBookName().equals(" "))){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_ILLEGAL);
        }
        Book book1 = bookMapper.getBookByNameOrId(book);
        String bookUrl =  BookUtil.document(book1.getBookUrl()).select("[class=hst]").attr("href").toString();
        Elements chapter = BookUtil.bookChapter(book1);
        Set<BookData> bookDatas = new HashSet<>();
        for (Element element : chapter) {
            BookData bookData = new BookData();
            bookData.setBookChapterUrl(bookUrl+element.select("a").attr("href"));
            bookData.setBookId(book1.getBookId());
            bookData.setId(GeneratIdUtil.getGeneratID(bookUrl));
            bookData.setBookChapter(element.select("a").text());
            bookDatas.add(bookData);
        }
        bookDataMapper.saveBookChapter(bookDatas);
        return bookMapper.getBookByNameOrId(book);
    }
}
