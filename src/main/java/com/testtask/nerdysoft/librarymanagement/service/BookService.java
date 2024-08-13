package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Book;

import java.util.List;

public interface BookService {
    public Book addBook(Book book);
    public Book getBookById(Long id);
    public List<Book> getAllBooks();
    public Book updateBookById(Long id, Book updatedBook);
    public void deleteBook(Long id);
}