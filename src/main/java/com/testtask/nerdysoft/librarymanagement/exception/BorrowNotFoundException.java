package com.testtask.nerdysoft.librarymanagement.exception;

public class BorrowNotFoundException extends RuntimeException {
    public BorrowNotFoundException() {
        super();
    }

    public BorrowNotFoundException(String message) {
        super(message);
    }
}
