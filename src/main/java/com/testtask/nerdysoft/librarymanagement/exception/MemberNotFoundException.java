package com.testtask.nerdysoft.librarymanagement.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super();
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
