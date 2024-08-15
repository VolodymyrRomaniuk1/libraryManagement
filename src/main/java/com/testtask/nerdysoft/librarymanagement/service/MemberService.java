package com.testtask.nerdysoft.librarymanagement.service;

import com.testtask.nerdysoft.librarymanagement.model.Member;

import java.util.List;

public interface MemberService {
    Member addMember(Member member);
    Member getMemberById(Long id);
    List<Member> getAllMembers();
    Member updateMemberById(Long id, Member updatedMember);
    void deleteMemberById(Long id);
}
