package com.testtask.nerdysoft.librarymanagement.service.impl;

import com.testtask.nerdysoft.librarymanagement.exception.MemberNotFoundException;
import com.testtask.nerdysoft.librarymanagement.model.Member;
import com.testtask.nerdysoft.librarymanagement.repository.MemberRepository;
import com.testtask.nerdysoft.librarymanagement.service.MemberService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final Validator validator;

    @Value("${library.maxBooksPerMember}")
    private int maxBooksPerMember;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, Validator validator) {
        this.memberRepository = memberRepository;
        this.validator = validator;
    }

    @Override
    public Member addMember(Member member) {
        validateMember(member);

        member.setMembershipDate(LocalDate.now());
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMemberById(Long id, Member updatedMember) {
        validateMember(updatedMember);
        Member existingMember = getMemberById(id);
        existingMember.setName(updatedMember.getName());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMemberById(Long id) {
        Member member = getMemberById(id);
        if (member.getBorrows().isEmpty()) {
            memberRepository.delete(member);
        } else {
            throw new IllegalStateException("Cannot delete Member with borrowed books.");
        }
    }

    private void validateMember(Member member) {
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                    .toList();
            throw new IllegalArgumentException("Provided Member has errors: " + errorMessages);
        }
    }
}
