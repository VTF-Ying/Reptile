package com.reptile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: Reptile
 * @ClassName: Book
 * @Description: TODO(书籍实体类)
 * @Author: VTF
 * @create: 2020-03-13 15:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;   //书籍ID
    private String bookUrl;  //书籍地址
    private String bookName; //书籍名
    private String bookAuthor;  //作者
    private Integer bookType;  //书籍类型
    private String bookData;  //书籍内容

}
