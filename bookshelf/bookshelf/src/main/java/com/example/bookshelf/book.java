package com.example.bookshelf;

public class book {
    private String title;
    private String author;
    private int pagesTotal;
    private int pageCurrent;

    public book(String title, String author, int pagesTotal, int pageCurrent) {
        this.title = title;
        this.author = author;
        this.pagesTotal = pagesTotal;
        this.pageCurrent = pageCurrent;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;

    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPagesTotal() {
        return pagesTotal;
    }
    public void setPagesTotal(int pagesTotal) {
        this.pagesTotal = pagesTotal;
    }
    public int getPageCurrent() {
        return pageCurrent;
    }
    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }
}
