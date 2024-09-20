package com.example.Back.service;

import com.example.Back.entity.Member;
import com.example.Back.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member get(String email){
        return memberRepository.findMemberByEmail(email).orElseThrow();
    }
}
