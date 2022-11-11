package com.likelion.hospital.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    @GetMapping("")
    public String index() {
        return "hospitals/index";
    }
}
