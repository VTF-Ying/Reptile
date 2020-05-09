package com.reptile;

import com.reptile.entity.Book;
import com.reptile.mapper.BookMapper;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.ArrayList;

@SpringBootTest
public class ReptileApplicationTests {

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

    Logger logger = LoggerFactory.getLogger(ReptileApplicationTests.class);
    @Test
    void test3() {  // slf4j测试
        // 这是日志的级别
        logger.trace("这是trace日志......跟踪轨迹");
        logger.debug("这是debug日志");
        // Sprig 默认使用info 级别的
        logger.info("这是info日志");
        logger.warn("这是警告日志");
        logger.error("这是错误日志");

    }
}
