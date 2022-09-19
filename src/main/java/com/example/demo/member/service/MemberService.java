package com.example.demo.member.service;



import com.example.demo.member.entity.MemberRepository;
import com.example.demo.member.service.request.UserInsertCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void memberInsert(UserInsertCommand command){
       memberRepository.save(command);
    }
}
