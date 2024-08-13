package com.testtask.nerdysoft.librarymanagement.service.impl;

import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.service.BookService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookService bookService;
    private Validator validator;

    @Autowired
    public BookServiceImpl(BookService bookService, Validator validator) {
        this.bookService = bookService;
        this.validator = validator;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }

    @Override
    public Book updateBookById(Long id, Book updatedBook) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }
}
