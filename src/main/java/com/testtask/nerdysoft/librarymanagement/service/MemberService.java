package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Member;

import java.util.List;

public interface MemberService {
    public Member addMember(Member member);
    public Member getMemberById(Long id);
    public List<Member> getAllMembers();
    public Member updateMember(Long id, Member updatedMember);
    public void deleteMember(Long id);
}
