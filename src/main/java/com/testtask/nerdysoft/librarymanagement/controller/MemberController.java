package com.testtask.nerdysoft.librarymanagement.controller;

import com.testtask.nerdysoft.librarymanagement.dto.LibraryMapper;
import com.testtask.nerdysoft.librarymanagement.dto.MemberDTO;
import com.testtask.nerdysoft.librarymanagement.model.Member;
import com.testtask.nerdysoft.librarymanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final LibraryMapper libraryMapper = LibraryMapper.INSTANCE;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO MemberDTO) {
        Member savedMember = memberService.addMember(libraryMapper.memberDTOToMember(MemberDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryMapper.memberToMemberDTO(savedMember));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        Member existingMember = memberService.getMemberById(id);
        return ResponseEntity.ok(libraryMapper.memberToMemberDTO(existingMember));
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members.stream().map(libraryMapper::memberToMemberDTO).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        Member updatedMember = memberService.updateMemberById(id, libraryMapper.memberDTOToMember(memberDTO));
        return ResponseEntity.ok(libraryMapper.memberToMemberDTO(updatedMember));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }
}
