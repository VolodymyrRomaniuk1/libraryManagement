package com.testtask.nerdysoft.librarymanagement.service.impl;

import com.testtask.nerdysoft.librarymanagement.exception.BookNotFoundException;
import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.repository.BookRepository;
import com.testtask.nerdysoft.librarymanagement.repository.BorrowRepository;
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
    private final BorrowRepository borrowRepository;
    private final Validator validator;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BorrowRepository borrowRepository, Validator validator) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
        this.validator = validator;
    }

    @Override
    public Book addBook(Book book) {
        validateBook(book);
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            if (book.getAmount() >= 1) {
                bookToUpdate.setAmount(bookToUpdate.getAmount() + book.getAmount());
            } else {
                bookToUpdate.setAmount(bookToUpdate.getAmount() + 1);
            }
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
        validateBook(updatedBook);
        Book bookToUpdate = getBookById(id);

        boolean isDuplicate = bookRepository.existsByTitleAndAuthorAndIdNot(updatedBook.getTitle(), updatedBook.getAuthor(), id);
        if (isDuplicate) {
            throw new IllegalStateException("Another Book with title \"" + updatedBook.getTitle() + "\" and author \"" + updatedBook.getAuthor() + "\" already exists");
        }

        bookToUpdate.setTitle(updatedBook.getTitle());
        bookToUpdate.setAuthor(updatedBook.getAuthor());
        bookToUpdate.setAmount(updatedBook.getAmount());

        return bookRepository.save(bookToUpdate);
    }

    @Override
    public void deleteBookById(Long id) {
        boolean isBookBorrowed = borrowRepository.existsByBookId(id);

        if (isBookBorrowed) {
            throw new IllegalStateException("Book cannot be deleted as it is currently borrowed.");
        }

        Book book = getBookById(id);
        bookRepository.delete(book);
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
