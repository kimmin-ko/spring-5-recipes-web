package com.mins.springrecipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public void hello(@RequestParam String name, Model model) {
        model.addAttribute("today", LocalDateTime.now());
        model.addAttribute("name", name);
    }

    @GetMapping("/world")
    public void helloWorld() {
        System.out.println("hello world!!");
    }

}
