package com.testtask.nerdysoft.librarymanagement.repository;

import com.testtask.nerdysoft.librarymanagement.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    boolean existsByBookId(Long bookId);
}