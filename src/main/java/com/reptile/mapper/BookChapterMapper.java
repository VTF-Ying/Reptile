package com.reptile.mapper;

import com.reptile.entity.BookChapter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookChapterMapper {
    int saveBookChapter(List<BookChapter> bookChapter);
    BookChapter selectChapterAll(String url);
    List<BookChapter> selectChapterAllById(Integer id);
}
