package com.testtask.nerdysoft.librarymanagement.service.impl;

import com.testtask.nerdysoft.librarymanagement.exception.BookNotFoundException;
import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.repository.BookRepository;
import com.testtask.nerdysoft.librarymanagement.service.BookService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Validator validator;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Validator validator) {
        this.bookRepository = bookRepository;
        this.validator = validator;
    }

    @Override
    public Book addBook(Book book) {
        validateBook(book);
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setAmount(bookToUpdate.getAmount() + 1);
            return bookRepository.save(bookToUpdate);
        } else {
            return bookRepository.save(book);
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBookById(Long id, Book updatedBook) {
        Book bookToUpdate = getBookById(id);
        bookToUpdate.setTitle(updatedBook.getTitle());
        bookToUpdate.setAuthor(updatedBook.getAuthor());
        bookToUpdate.setAmount(updatedBook.getAmount());
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(bookToUpdate.getTitle(), bookToUpdate.getAuthor());
        // todo deletion of the entry while its id is being referenced somewhere
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        // todo prevent deletion if book is borrowed
        if (book.getAmount() == 0) {
            bookRepository.delete(book);
        } else {
            throw new IllegalStateException("Cannot delete book that is currently borrowed.");
        }
    }

    private void validateBook(Book book) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                    .toList();
            throw new IllegalArgumentException("Provided Book has errors: " + errorMessages);
        }
    }
}
