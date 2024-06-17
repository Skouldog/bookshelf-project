package com.example.bookshelf;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuoteRepository extends ListCrudRepository<Quotes, Long> {
    @Query("SELECT * FROM QUOTES WHERE QUOTE = :quote")
    Quotes findByQuote(String quote);

    @Query("""
SELECT QUOTES.* FROM QUOTES
JOIN ALLBOOKS on QUOTES.ALLBOOKS_ID = ALLBOOKS.ID
WHERE lower(ALLBOOKS.TITLE)=lower(:title)
""")
    Quotes findByTitle(String title);

}
