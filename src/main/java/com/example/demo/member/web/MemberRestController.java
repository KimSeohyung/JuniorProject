package com.example.demo.member.web;


import com.example.demo.member.service.MemberService;
import com.example.demo.member.service.request.UserInsertCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MemberRestController {

    @Autowired
    MemberService memberService;

    @PostMapping("/joinAdd")
    public String joinAdd(UserInsertCommand command) {

        System.out.println(command);
        memberService.memberInsert(command);

        return "home";



    }
}
