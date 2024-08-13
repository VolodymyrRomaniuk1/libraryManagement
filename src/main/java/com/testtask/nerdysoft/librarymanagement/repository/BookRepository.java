package com.testtask.nerdysoft.librarymanagement.repository;

import com.testtask.nerdysoft.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
}
