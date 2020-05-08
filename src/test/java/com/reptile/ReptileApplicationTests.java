package com.reptile;

import com.reptile.entity.Book;
import com.reptile.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.ArrayList;

@SpringBootTest
class ReptileApplicationTests {

    @Autowired
    private BookMapper bookMapper;
    @Test
    void contextLoads() {  // 空值测试
        List<Book> list = bookMapper.getAllBook();
        for (Book book : list) {
            System.out.println(book);
        }

        List list1= new ArrayList();
        Set set = new HashSet();
        System.out.println(list1.size());
    }
    @Test
    void test2() {  // final 关键字测试

        int num = 200;
        System.out.println(num);
        final Book book = new Book("伍佰");
        System.out.println(book.getBookName());
        book.setBookName("六百");
        System.out.println(book.getBookName());
    }
}
