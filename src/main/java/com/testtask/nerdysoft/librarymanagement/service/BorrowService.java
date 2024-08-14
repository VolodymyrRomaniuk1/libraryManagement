package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Borrow;

import java.util.List;

public interface BorrowService {
    public Borrow borrowBook(Long memberId, Long bookId);
    public void returnBook(Long borrowId);
    public Borrow getBorrowById(Long borrowId);
    public List<Borrow> getAllBorrows();
}
