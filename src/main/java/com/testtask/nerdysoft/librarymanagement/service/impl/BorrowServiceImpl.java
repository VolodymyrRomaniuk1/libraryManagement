package com.testtask.nerdysoft.librarymanagement.service.impl;

import com.testtask.nerdysoft.librarymanagement.exception.BorrowNotFoundException;
import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.model.Borrow;
import com.testtask.nerdysoft.librarymanagement.model.Member;
import com.testtask.nerdysoft.librarymanagement.repository.BorrowRepository;
import com.testtask.nerdysoft.librarymanagement.service.BookService;
import com.testtask.nerdysoft.librarymanagement.service.BorrowService;
import com.testtask.nerdysoft.librarymanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final MemberService memberService;

    @Value("${library.maxBooksPerMember}")
    private int maxBooksPerMember;

    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository, BookService bookService, MemberService memberService) {
        this.borrowRepository = borrowRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }


    @Override
    public Borrow borrowBook(Long memberId, Long bookId) {
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookById(bookId);

        if (member.getBorrows().stream().filter(b -> b.getReturnDate() == null).count() >= maxBooksPerMember) {
            throw new IllegalStateException("Member has reached the maximum number of borrowed books.");
        }

        if (book.getAmount() == 0) {
            throw new IllegalStateException("No copies of this book are currently available for borrowing.");
        }

        Borrow borrow = Borrow.builder()
                .member(member)
                .book(book)
                .borrowDate(LocalDate.now())
                .build();

        book.setAmount(book.getAmount() - 1);
        bookService.updateBookById(book.getId(), book);

        return borrowRepository.save(borrow);
    }

    @Override
    public void returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow record not found with id: " + borrowId));

        if(borrow.getReturnDate() != null) {
            throw new IllegalArgumentException("Book has already been returned.");
        }

        Book book = borrow.getBook();
        book.setAmount(book.getAmount() + 1);
        bookService.updateBookById(book.getId(), book);

        borrow.setReturnDate(LocalDate.now());
        borrowRepository.save(borrow);
    }

    @Override
    public Borrow getBorrowById(Long borrowId) {
        return borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow not found with id: " + borrowId));
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }
}
