package com.reptile.mapper;

import com.reptile.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    Book getBookById(@Param("bookId")Long bookId);

}
