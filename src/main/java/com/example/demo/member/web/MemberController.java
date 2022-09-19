package com.example.demo.member.web;


import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/join")
    public String join() { return "memberJoin";}


}
