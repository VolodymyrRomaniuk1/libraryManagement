package com.testtask.nerdysoft.librarymanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(BookNotFoundException exception) {
        log.info("BookNotFoundException thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(MemberNotFoundException exception) {
        log.info("MemberNotFoundException thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(BorrowNotFoundException exception) {
        log.info("BorrowNotFoundException thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.info("IllegalArgumentException thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }


    @ExceptionHandler
    public ResponseEntity<?> handleIllegalStateExceptionException(IllegalStateException exception) {
        log.info("IllegalStateException thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException exception) {
        log.info("ResponseStatusException thrown: " + exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
    }
}
