package com.example.bookshelf;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Table("ALLBOOKS")
public class AllBooks {
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

@Column("BOOK_URL")
    private String booKUrl;

static int progress;


    public AllBooks(String title, String author, int pagesTotal, int pageCurrent, String booKUrl) {
        this.title = title;
        this.author = author;
        this.pagesTotal = pagesTotal;
        this.pageCurrent = pageCurrent;
        this.booKUrl = booKUrl;
         progress = (int) Math.ceil( pageCurrent*100 / pagesTotal); ;
    }




    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
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
    public String getBooKUrl() {return booKUrl;}
    public void setBooKUrl(String booKUrl) {this.booKUrl = booKUrl;}
    //Overrides damit die Equals funktion richtig vergleicht
    public void setProgress() {
        progress = (int) Math.ceil( pageCurrent*100 / pagesTotal);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AllBooks otherBook = (AllBooks) obj;
        return Objects.equals(this.title, otherBook.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}

