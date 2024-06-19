package com.example.bookshelf.repos;

import com.example.bookshelf.Quotes;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IQuoteRepository extends ListCrudRepository<Quotes, Long> {
    @Query("SELECT * FROM QUOTES WHERE QUOTE = :quote")
    Quotes findByQuote(String quote);



    @Query("SELECT * FROM QUOTES WHERE ALLBOOKS_ID = :id")
    List<Quotes> findAllQuotesByBookId(Long id);


    @Query("""
SELECT QUOTES.* FROM QUOTES
JOIN ALLBOOKS ON QUOTES.ALLBOOKS_ID = ALLBOOKS.ID
WHERE LOWER(ALLBOOKS.TITLE) = LOWER(:title)
""")
    Set<Quotes> findQuotesByBookTitle(String title);
}
