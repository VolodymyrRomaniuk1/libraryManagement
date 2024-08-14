package com.testtask.nerdysoft.librarymanagement.dto;

import com.testtask.nerdysoft.librarymanagement.model.Book;
import com.testtask.nerdysoft.librarymanagement.model.Borrow;
import com.testtask.nerdysoft.librarymanagement.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LibraryMapper {
    LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

    // Mapping for Book entity to BookDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "amount", target = "amount")
    BookDTO bookToBookDTO(Book book);

    // Mapping for BookDTO to Book entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "amount", target = "amount")
    Book bookDTOToBook(BookDTO bookDTO);

    // Mapping for Member entity to MemberDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "membershipDate", target = "membershipDate")
    MemberDTO memberToMemberDTO(Member member);

    // Mapping for MemberDTO to Member entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "membershipDate", target = "membershipDate")
    Member memberDTOToMember(MemberDTO memberDTO);

    // Mapping for Borrow entity to BorrowDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "borrowDate", target = "borrowDate")
    @Mapping(source = "returnDate", target = "returnDate")
    BorrowDTO borrowToBorrowDTO(Borrow borrow);

    // Mapping for BorrowDTO to Borrow entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "memberId", target = "member")
    @Mapping(source = "bookId", target = "book")
    @Mapping(source = "borrowDate", target = "borrowDate")
    @Mapping(source = "returnDate", target = "returnDate")
    Borrow borrowDTOToBorrow(BorrowDTO borrowDTO);

    // Method to map memberId to Member
    default Member mapMemberIdToMember(Long memberId) {
        if (memberId == null) {
            return null;
        }
        Member member = new Member();
        member.setId(memberId);
        return member;
    }

    // Method to map bookId to Book
    default Book mapBookIdToBook(Long bookId) {
        if (bookId == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookId);
        return book;
    }

    // Method to map Member to memberId
    default Long mapMemberToMemberId(Member member) {
        if (member == null) {
            return null;
        }
        return member.getId();
    }

    // Method to map Book to bookId
    default Long mapBookToBookId(Book book) {
        if (book == null) {
            return null;
        }
        return book.getId();
    }
}
