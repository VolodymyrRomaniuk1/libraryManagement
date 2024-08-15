package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.repository.BookRepository;
import com.testtask.nerdysoft.librarymanagement.repository.BorrowRepository;
import com.testtask.nerdysoft.librarymanagement.service.impl.BookServiceImpl;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BorrowRepository borrowRepository;
    @Mock
    private Validator validator;

    private Long bookId = 1L;
    private String author = "John Doe";
    private String title = "Foo title";
    private int amount = 3;

    @Test
    @DisplayName("Test addBook() (Positive scenarios)")
    public void testAddBook() {
        Book bookToSave = Book.builder()
                .author(author)
                .title(title)
                .amount(amount)
                .build();

        // Mock repository behavior
        Book savedBook = Book.builder()
                .id(bookId)
                .author(author)
                .title(title)
                .amount(amount)
                .build();

        when(validator.validate(bookToSave)).thenReturn(Collections.emptySet());
        when(bookRepository.save(bookToSave)).thenReturn(savedBook);

        // Call service method
        Book addedBook = bookService.addBook(bookToSave);

        // Assertions
        assertNotNull(addedBook);
        assertEquals(author, addedBook.getAuthor());
        assertEquals(title, addedBook.getTitle());
        assertEquals(amount, addedBook.getAmount());

        verify(bookRepository, times(1)).save(bookToSave);
    }

    @Test
    @DisplayName("Test getBookById() (Positive scenarios)")
    public void testGetBookById() {
        Book expectedBook = Book.builder()
                .id(bookId)
                .author(author)
                .title(title)
                .amount(amount)
                .build();

        // Mock repository behavior
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        // Call service method
        Book actualBook = bookService.getBookById(bookId);

        // Assertions
        assertNotNull(actualBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Test updateBookById() (Positive scenarios)")
    public void testUpdateBook() {
        Book bookToUpdate = Book.builder()
                .id(bookId)
                .author(author)
                .title(title)
                .amount(amount)
                .build();

        Book savedBook = Book.builder()
                .id(bookId)
                .author(author)
                .title(title)
                .amount(amount)
                .build();

        // Mock repository behavior
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookToUpdate));
        when(bookRepository.existsByTitleAndAuthorAndIdNot(any(String.class), any(String.class), any(Long.class))).thenReturn(false);
        when(bookRepository.save(bookToUpdate)).thenReturn(savedBook);


        // Call service method
        Book updatedBook = bookService.updateBookById(bookId, bookToUpdate);

        // Assertions
        assertNotNull(updatedBook);
        assertEquals(author, updatedBook.getAuthor());
        assertEquals(title, updatedBook.getTitle());
        assertEquals(amount, updatedBook.getAmount());

        verify(bookRepository, times(1)).save(updatedBook);
    }

    @Test
    @DisplayName("Test deleteBookById() (Positive scenarios)")
    public void testDeleteBook() {
        Book bookToDelete = Book.builder()
                .id(bookId)
                .author(author)
                .title(title)
                .amount(amount)
                .build();

        // Mock repository behavior
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookToDelete));
        when(borrowRepository.existsByBookId(bookId)).thenReturn(false);

        // Call service method
        bookService.deleteBookById(bookId);

        verify(bookRepository, times(1)).delete(bookToDelete);
    }
}
