package com.mins.springrecipes.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId) {
        return "회원 아이디는 " + memberId + "입니다.";
    }

}
