package com.mabini.meditrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dashboard")
@SessionAttributes("studentAccount")
public class StudentDashboard {

    @GetMapping
    public String dashboard(Model model) {
        log.info("model from get: " + model.asMap().values().toString());
        return "dashboard";
    }
    
}
