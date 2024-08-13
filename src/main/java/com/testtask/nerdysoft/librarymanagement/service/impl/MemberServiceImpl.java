package com.testtask.nerdysoft.librarymanagement.service.impl;

import com.testtask.nerdysoft.librarymanagement.model.Member;
import com.testtask.nerdysoft.librarymanagement.service.MemberService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberService memberService;

    @Autowired
    public MemberServiceImpl(MemberService memberService, Validator validator) {
        this.memberService = memberService;
    }

    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public Member updateMember(Long id, Member updatedMember) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}
