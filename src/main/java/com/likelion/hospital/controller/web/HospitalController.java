package com.likelion.hospital.controller.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    @GetMapping("")
    public String index(Model model, @Nullable @RequestParam("city") String city, @PageableDefault Pageable pageable) {
        model.addAttribute("city", (city != null) ? city : "");
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        return "hospitals/index";
    }
}
