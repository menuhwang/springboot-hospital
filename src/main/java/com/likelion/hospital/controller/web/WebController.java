package com.likelion.hospital.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/users/logout")
    public String logout() {
        return "users/logout";
    }
}
