package com.testtask.nerdysoft.librarymanagement.repository;

import com.testtask.nerdysoft.librarymanagement.dto.BookSummaryDTO;
import com.testtask.nerdysoft.librarymanagement.model.Borrow;
import com.testtask.nerdysoft.librarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    boolean existsByBookId(Long bookId);
    List<Borrow> findByMember(Member member);

    @Query("SELECT DISTINCT b.title FROM Borrow br JOIN br.book b")
    List<String> findDistinctBookNames();

    @Query("SELECT new com.testtask.nerdysoft.librarymanagement.dto.BookSummaryDTO(b.title, COUNT(b)) " +
           "FROM Borrow br JOIN br.book b " +
           "GROUP BY b.title")
    List<BookSummaryDTO> findBorrowedBookSummary();
}