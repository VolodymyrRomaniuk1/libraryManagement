package com.testtask.nerdysoft.librarymanagement.controller;

import com.testtask.nerdysoft.librarymanagement.dto.BookDTO;
import com.testtask.nerdysoft.librarymanagement.dto.BookSummaryDTO;
import com.testtask.nerdysoft.librarymanagement.dto.BorrowDTO;
import com.testtask.nerdysoft.librarymanagement.dto.LibraryMapper;
import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.model.Borrow;
import com.testtask.nerdysoft.librarymanagement.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;
    private final LibraryMapper libraryMapper = LibraryMapper.INSTANCE;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<BorrowDTO> borrowBook(@RequestBody BorrowDTO borrowDTO) {
        Borrow createdBorrow = borrowService.borrowBook(borrowDTO.getMemberId(), borrowDTO.getBookId());
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryMapper.borrowToBorrowDTO(createdBorrow));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getBorrowById(@PathVariable Long id) {
        Borrow borrow = borrowService.getBorrowById(id);
        return ResponseEntity.ok(libraryMapper.borrowToBorrowDTO(borrow));
    }

    @GetMapping
    public ResponseEntity<List<BorrowDTO>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows.stream().map(libraryMapper::borrowToBorrowDTO).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        borrowService.returnBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<List<BookDTO>> getBooksBorrowedByMember(@PathVariable Long id) {
        List<Book> books = borrowService.getBooksBorrowedByMemberId(id);
        return ResponseEntity.ok(books.stream().map(libraryMapper::bookToBookDTO).collect(Collectors.toList()));
    }

    @GetMapping("/distinct-books")
    public ResponseEntity<List<String>> getDistinctBorrowedBookNames() {
        List<String> bookNames = borrowService.getDistinctBorrowedBookNames();
        return ResponseEntity.ok(bookNames);
    }

    @GetMapping("/book-summary")
    public ResponseEntity<List<BookSummaryDTO>> getBorrowedBookSummary() {
        List<BookSummaryDTO> bookSummaries = borrowService.getBorrowedBookSummary();
        return ResponseEntity.ok(bookSummaries);
    }
}
