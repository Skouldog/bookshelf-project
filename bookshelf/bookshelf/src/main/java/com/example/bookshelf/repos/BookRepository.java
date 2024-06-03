package com.example.bookshelf.repos;


import com.example.bookshelf.book;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<book, Long> {
}
