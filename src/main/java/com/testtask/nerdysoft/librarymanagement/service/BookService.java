package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book updateBookById(Long id, Book updatedBook);
    void deleteBookById(Long id);
}