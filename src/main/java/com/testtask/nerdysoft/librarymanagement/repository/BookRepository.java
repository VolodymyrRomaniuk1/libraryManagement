package com.testtask.nerdysoft.librarymanagement.repository;

import com.testtask.nerdysoft.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
    boolean existsByTitleAndAuthor(String title, String author);
    boolean existsByTitleAndAuthorAndIdNot(String title, String author, Long id);
}
