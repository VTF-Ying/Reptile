package com.reptile.mapper;

import com.reptile.entity.BookData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @ProjectName: Reptile
 * @ClassName: saveBookChapter
 * @Description: TODO(一句话描述该类的功能)
 * @Author: VTF
 * @create: 2020-03-20 17:02
 */
@Mapper
@Repository
public interface BookDataMapper {

    int saveBookChapter(@Param("bookData") Set<BookData> bookData);


}
