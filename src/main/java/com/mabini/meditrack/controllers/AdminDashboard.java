package com.mabini.meditrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/dashboard")
@SessionAttributes("admin")
public class AdminDashboard {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("isStudentExist", false);
    }
    
    
    @GetMapping()
    public String adminDashboard(Model model) {
        log.info("Model: {}", model.toString());
        return "adminDashboard";
    }
    
}
