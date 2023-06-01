package com.mabini.meditrack.controllers;

import com.mabini.meditrack.mappers.StudentAccountMapper;
import com.mabini.meditrack.models.Student;
import com.mabini.meditrack.models.StudentAccount;
import com.mabini.meditrack.services.IStudentAccountService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
@SessionAttributes("studentAccount")
public class Home {

    private final IStudentAccountService studentAccountService;

    public Home(IStudentAccountService studentAccountService) {
        this.studentAccountService = studentAccountService;
    }

    @ModelAttribute("studentAccount")
    public StudentAccount studentAccount() {
        return new StudentAccount();
    }

    @ModelAttribute("studentAccountMapper")
    public StudentAccountMapper studentAccountMapper() {
        return new StudentAccountMapper();
    }

    @GetMapping("/")
    public String home(Model model) {
        log.info("model from get: " + model.asMap().values().toString());
        return "index";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute StudentAccount studentAccount, Errors errors, Model model) {

        if (studentAccountService.login(studentAccount.getEmail(), studentAccount.getPassword()) != null) {

            String email = studentAccount.getEmail();
            String password = studentAccount.getPassword();

            StudentAccount account = studentAccountService.login(email, password);

            model.addAttribute("studentAccount", account);

            log.info("Success login: " + studentAccount);

            return "redirect:/dashboard";
        }

        errors.rejectValue("email", "email", "Invalid email or password");
        if (errors.hasErrors()) {
            log.info("Errors: " + errors.getAllErrors().toString());
        }
        return "index";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute StudentAccountMapper studentAccountMapper, Errors errors) {

        if (errors.hasErrors()) {
            if (!studentAccountMapper.getPassword().equals(studentAccountMapper.getConfirmedPassword())) {
                errors.rejectValue("confirmedPassword", "confirmedPassword", "Password does not match");
            }
            log.info("Errors: " + errors.getAllErrors().toString());
            return "index";
        }

        log.info("Student: " + studentAccountMapper.toString()); // TODO: Create a sign up service

        StudentAccount account = studentAccountService.register(studentAccountMapper);

        if (account == null) {
            errors.rejectValue("LRN", "LRN", "LRN does not exist or account already exists");
            log.info("Errors: " + errors.getAllErrors().toString());
            return "index";
        }

        return "redirect:/";
    }

}
