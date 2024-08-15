package com.testtask.nerdysoft.librarymanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BorrowDTO {

    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDate borrowDate;
}
