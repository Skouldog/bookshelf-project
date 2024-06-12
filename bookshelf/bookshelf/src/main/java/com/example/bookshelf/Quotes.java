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

    public Quotes(String quote) {
        this.quote = quote;
    }

    public Quotes() {
    }
}
