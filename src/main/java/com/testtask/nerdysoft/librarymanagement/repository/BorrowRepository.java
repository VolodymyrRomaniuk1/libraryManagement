package com.testtask.nerdysoft.librarymanagement.repository;

import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.model.Borrow;
import com.testtask.nerdysoft.librarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    boolean existsByMemberAndReturnDateIsNull(Member member);
    boolean existsByBookAndReturnDateIsNull(Book book);
}