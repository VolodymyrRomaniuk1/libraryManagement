package com.testtask.nerdysoft.librarymanagement.controller;

import com.testtask.nerdysoft.librarymanagement.dto.BookDTO;
import com.testtask.nerdysoft.librarymanagement.dto.LibraryMapper;
import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final LibraryMapper libraryMapper = LibraryMapper.INSTANCE;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book savedBook = bookService.addBook(libraryMapper.bookDTOToBook(bookDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryMapper.bookToBookDTO(savedBook));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book existingBook = bookService.getBookById(id);
        return ResponseEntity.ok(libraryMapper.bookToBookDTO(existingBook));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books.stream().map(libraryMapper::bookToBookDTO).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book updatedBook = bookService.updateBookById(id, libraryMapper.bookDTOToBook(bookDTO));
        return ResponseEntity.ok(libraryMapper.bookToBookDTO(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
