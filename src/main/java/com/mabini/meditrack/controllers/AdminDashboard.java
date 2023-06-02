package com.mabini.meditrack.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mabini.meditrack.mappers.StudentLrnMapper;
import com.mabini.meditrack.models.Admin;
import com.mabini.meditrack.models.Examination;
import com.mabini.meditrack.models.MedicalHistory;
import com.mabini.meditrack.models.Record;
import com.mabini.meditrack.models.Student;
import com.mabini.meditrack.services.IAdminService;

import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/dashboard")
@SessionAttributes({ "admin", "student", "medicalHistory", "examination", "medicalHistoryList", "examinationList" })
public class AdminDashboard {

    public final IAdminService adminService;

    public AdminDashboard(IAdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("isStudentExist", false);

    }

    @ModelAttribute("student")
    public Student student() {
        return new Student();
    }

    @ModelAttribute("record")
    public Record record() {
        return new Record();
    }

    @ModelAttribute("medicalHistory")
    public MedicalHistory medicalHistory() {
        return new MedicalHistory();
    }

    @ModelAttribute("examination")
    public Examination examination() {
        return new Examination();
    }

    @ModelAttribute("medicalHistoryList")
    public List<MedicalHistory> medicalHistoryList() {
        return new ArrayList<MedicalHistory>();
    }

    @ModelAttribute("examinationList")
    public List<Examination> examinationList() {
        return new ArrayList<Examination>();
    }

    @GetMapping()
    public String adminDashboard(Model model) {
        return "adminDashboard";
    }

    @PostMapping("/findStudent")
    public String findStudent(@Valid @ModelAttribute Student student, Errors errors, Model model) {

        long LRN = student.getLRN();

        Student foundStudent = adminService.findStudent(LRN);

        if (foundStudent == null) {
            errors.rejectValue("LRN", "LRN", "Student not found");
            return "adminDashboard";
        }

        model.addAttribute("student", foundStudent);
        model.addAttribute("isStudentExist", true);
        log.info("Found Student: {}", model.toString());
        return "adminDashboard";
    }

    @PostMapping("/addRecord")
    public String addRecord(
            @Valid @ModelAttribute Record record,
            @ModelAttribute Student student,
            Model model,
            Errors errors) {

        log.info(model.asMap().get("student").toString());

        record.setStudent((Student) model.getAttribute("student"));
        record.setAdmin((Admin) model.getAttribute("admin"));

        log.info("NEW RECORD: {}", record.toString());

        return "adminDashboard";
    }

    @PostMapping("/addMedicalHistory")
    public String addMedicalRecord(
            @Valid MedicalHistory medicalHistory,
            @ModelAttribute List<MedicalHistory> medicalHistories,
            Model model,
            Errors errors) {

        if (errors.hasErrors()) {
            return "adminDashboard";
        }

        medicalHistories.add(medicalHistory);
        log.info("", medicalHistories.toString());
        log.info("NEW MEDICAL HISTORY: {}", medicalHistory.toString());

        return "adminDashboard";
    }

    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/admin";
    }

}
