package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Borrow;

public interface BorrowService {
    public Borrow borrowBook(Long memberId, Long bookId);
    public void returnBook(Long borrowId);
}
