package com.reptile.mapper;

import com.reptile.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: Reptile
 * @ClassName: BookMapper
 * @Description: TODO(书籍数据访问接口)
 * @Author: VTF
 * @create: 2020-03-14 09:29
 */
@Mapper
@Repository
public interface BookMapper {

    int saveBooks(@Param("set") Set<Book> set);

    List<Book> getAllBook();

    Book getBookById(Book book);

    Book getBookByName(Book book);

}
