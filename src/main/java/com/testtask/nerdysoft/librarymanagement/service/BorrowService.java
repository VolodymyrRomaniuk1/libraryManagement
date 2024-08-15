package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.dto.BookSummaryDTO;
import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.model.Borrow;

import java.util.List;

public interface BorrowService {
    Borrow borrowBook(Long memberId, Long bookId);
    void returnBook(Long borrowId);
    Borrow getBorrowById(Long borrowId);
    List<Borrow> getAllBorrows();
    List<Book> getBooksBorrowedByMemberId(Long id);
    List<String> getDistinctBorrowedBookNames();
    List<BookSummaryDTO> getBorrowedBookSummary();
}
