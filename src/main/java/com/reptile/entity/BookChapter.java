package com.reptile.entity;

public class BookChapter {
    private int bookId;
    private int bookDetailsId;
    private String bookChapter;
    private String bookChapterUrl;
    private String upTime;

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookDetailsId() {
        return bookDetailsId;
    }

    public void setBookDetailsId(int bookDetailsId) {
        this.bookDetailsId = bookDetailsId;
    }

    public String getBookChapter() {
        return bookChapter;
    }

    public void setBookChapter(String bookChapter) {
        this.bookChapter = bookChapter;
    }

    public String getBookChapterUrl() {
        return bookChapterUrl;
    }

    public void setBookChapterUrl(String bookChapterUrl) {
        this.bookChapterUrl = bookChapterUrl;
    }
}
