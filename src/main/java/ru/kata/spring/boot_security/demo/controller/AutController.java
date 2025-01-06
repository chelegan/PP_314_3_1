package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutController {

    @GetMapping("/admin")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "userHome";
    }

}
