package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Borrow;

import java.util.List;

public interface BorrowService {
    Borrow borrowBook(Long memberId, Long bookId);
    void returnBook(Long borrowId);
    Borrow getBorrowById(Long borrowId);
    List<Borrow> getAllBorrows();
}
