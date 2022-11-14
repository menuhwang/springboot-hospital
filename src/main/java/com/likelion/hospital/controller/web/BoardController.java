package com.likelion.hospital.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @GetMapping("")
    public String index() {
        return "boards/index";
    }

    @GetMapping("/new")
    public String create() {
        return "boards/new";
    }

    @GetMapping("/{id}")
    public String getPost(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "boards/post";
    }
}
