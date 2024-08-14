package com.testtask.nerdysoft.librarymanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MemberDTO {

    private Long id;
    private String name;
    private LocalDate membershipDate;

}
