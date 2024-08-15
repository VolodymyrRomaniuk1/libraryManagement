package com.testtask.nerdysoft.librarymanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookSummaryDTO {
    private String bookName;
    private Long count;
}
