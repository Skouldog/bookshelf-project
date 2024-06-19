package com.example.bookshelf;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("QUOTES")
public class Quotes {

    @Id
    @Column("ID")
    private Long id;
    @Column("QUOTE")
    private String quote;

    @Column("ALLBOOKS_ID")
    private Long bookId;


    public Quotes(String quote, AllBooks book) {
        this.quote = quote;
        this.bookId = book.getId();
    }

    public Quotes() {
    }


    public String getQuote() {
        return quote;
    }

}