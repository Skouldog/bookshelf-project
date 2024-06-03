package com.example.bookshelf;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("BOOKS")
public class book {
    @Id
    @Column("ID")
    private Long id;
    @Column("TITLE")
    private String title;
    @Column("AUTHOR")
    private String author;

    @Column("PAGES_TOTAL")
    private int pagesTotal;
    @Column("PAGE_CURRENT")
    private int pageCurrent;

    public book(String title, String author, int pagesTotal, int pageCurrent) {
        this.title = title;
        this.author = author;
        this.pagesTotal = pagesTotal;
        this.pageCurrent = pageCurrent;
    }

    public book() {
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
