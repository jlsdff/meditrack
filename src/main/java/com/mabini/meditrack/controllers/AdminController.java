package com.mabini.meditrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.mabini.meditrack.models.Admin;

import com.mabini.meditrack.mappers.AdminMapper;
import com.mabini.meditrack.services.IAdminService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("admin")
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService){
        this.adminService = adminService;
    }

    @ModelAttribute("adminMapper")
    public AdminMapper adminMapper() {
        return new AdminMapper();
    }
    @ModelAttribute("admin")
    public Admin admin() {
        return new Admin();
    }
    
    
    @GetMapping()
    public String admin(Model model) {
        return "admin";
    }

    @PostMapping
    public String adminPost(@Valid @ModelAttribute AdminMapper adminMapper, Errors errors, Model model) {

        String email = adminMapper.getEmail();
        String password = adminMapper.getPassword();

        Admin admin = adminService.login(email, password);
        
        if(admin == null){
            errors.rejectValue("email","email", "Invalid email or password");
            return "admin";
        }

        if (errors.hasErrors()) {
            log.info("Error: " + errors.getAllErrors().toString());
            return "admin";
        }

        model.addAttribute("admin", admin);
        model.asMap().remove("adminMapper"); 

        log.info("Success Admin Login: " + model.toString());

        return "redirect:/admin/dashboard";
    }
    
    
}
