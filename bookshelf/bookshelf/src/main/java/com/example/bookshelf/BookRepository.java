package com.example.bookshelf;


import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<book, Long> {
}
