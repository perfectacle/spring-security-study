package com.example.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping({"", "home"})
    public String home() {
        return "home";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

}
