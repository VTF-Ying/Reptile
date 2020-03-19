package com.reptile.service;

import com.reptile.entity.Book;
import com.reptile.mapper.BookMapper;
import com.reptile.util.BookUtil;
import com.reptile.util.ResponseCode;
import com.reptile.util.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * @Author VTF
     * @Description //获取更新的书籍列表
     * @Param
     **/
    public Set<Book> getUpdateBook(String url) {

        Set<Book> books = BookUtil.bookData(url);
        HashSet<Book> listBooks = new HashSet<>();
        for (Book book : books) {
            Book book1 = bookMapper.getBookByName(book);
            if (book1 == null){
                listBooks.add(book);
            }
        }
        if(listBooks.size()==0){
            return listBooks;
        }
        bookMapper.saveBooks(listBooks);
    return listBooks;
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
     * @Description //根据书籍ID获取书的信息
     * @Param
     **/
    public Book getBookById(Book book){
        if (book.getBookId() == null || book.getBookId()==0){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_ILLEGAL);
        }
        return bookMapper.getBookById(book) ;
    }

    /**
     * @Author VTF
     * @Description //根据书籍名称查询单本书籍
     * @Param
     **/
    public Book getBookByName(Book book){
        if (book.getBookName() == null || book.getBookName().equals(" ")){
            throw new ApplicationException(ResponseCode.INPUT_VALUE_IS_ILLEGAL);
        }
        return bookMapper.getBookByName(book) ;
    }
}
