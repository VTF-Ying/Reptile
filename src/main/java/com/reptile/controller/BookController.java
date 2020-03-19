package com.reptile.controller;

import com.reptile.entity.Book;
import com.reptile.service.BookService;
import com.reptile.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: Reptile
 * @ClassName: BookController
 * @Description: TODO(书籍的表现层控制类)
 * @Author: VTF
 * @create: 2020-03-13 15:46
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取更新的书籍列表
     * @return
     */
    @RequestMapping(value = "/book/getUpdateBook", method = RequestMethod.POST)
    public ResponseData<Set<Book>> getUpdateBook(@RequestBody Book book){
        ResponseData<Set<Book>> resp = new ResponseData<Set<Book>>();
        Set<Book> updateBook = bookService.getUpdateBook(book.getBookUrl());
        resp.setData(updateBook).ok();
        return resp;
    }

    /**
     * @Author VTF
     * @Description 查询所有书籍
     * @Param
     **/
    @RequestMapping(value = "/book/getAllBook", method = RequestMethod.POST)
    public ResponseData<List<Book>> getAllBook(){
        ResponseData<List<Book>> resp = new ResponseData<List<Book>>();
        resp.setData(bookService.getAllBook()).ok();
        return resp;
    }

    /**
     * 根据ID查询单本书籍
     * @param book
     * @return
     */
    @RequestMapping(value = "/book/getBookById", method = RequestMethod.POST)
    public ResponseData<Book> getBookById(@RequestBody Book book){
        ResponseData<Book> resp = new ResponseData<Book>();
        resp.setData(bookService.getBookById(book)).ok();
        return resp;
    }

    /**
     * 根据书籍名称查询单本书籍
     * @param book
     * @return
     */
    @RequestMapping(value = "/book/getBookByName", method = RequestMethod.POST)
    public ResponseData<Book> getgetBookByName(@RequestBody Book book){
        ResponseData<Book> resp = new ResponseData<Book>();
        resp.setData(bookService.getBookByName(book)).ok();
        return resp;
    }


}
