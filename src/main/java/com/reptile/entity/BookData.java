package com.reptile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: Reptile
 * @ClassName: BookData
 * @Description: TODO(书籍章节以及详情)
 * @Author: VTF
 * @create: 2020-03-19 09:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookData {
    private Long id;             //章节 ID
    private Long bookId;        //书籍 ID
    private String bookChapter; //章节名
    private String bookChapterUrl; // 章节内容url
}
