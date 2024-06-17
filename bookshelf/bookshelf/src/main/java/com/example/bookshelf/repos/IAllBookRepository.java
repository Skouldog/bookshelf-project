package com.example.bookshelf.repos;


import com.example.bookshelf.AllBooks;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAllBookRepository extends ListCrudRepository<AllBooks, Long> {

    @Query("SELECT * FROM ALLBOOKS WHERE TITLE = :title")
    AllBooks findByTitle(String title);
    List<AllBooks> findByTitleContainingIgnoreCase(String title);


}
