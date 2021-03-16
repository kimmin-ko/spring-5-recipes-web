package com.mins.springrecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public String hello(Model model) {
        model.addAttribute("today", LocalDateTime.now());
        return "hello";
    }

}

