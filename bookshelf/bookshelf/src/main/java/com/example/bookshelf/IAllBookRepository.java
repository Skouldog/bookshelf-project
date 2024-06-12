package com.example.bookshelf;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IAllBookRepository extends ListCrudRepository<AllBooks, Long> {

    @Query("SELECT * FROM ALLBOOKS WHERE TITLE = :title")
    AllBooks findByTitle(String title);




}
