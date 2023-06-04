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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mabini.meditrack.mappers.StudentLrnMapper;
import com.mabini.meditrack.models.Admin;
import com.mabini.meditrack.models.Examination;
import com.mabini.meditrack.models.MedicalHistory;
import com.mabini.meditrack.models.Record;
import com.mabini.meditrack.models.Student;
import com.mabini.meditrack.services.IAdminService;
import com.mabini.meditrack.services.IRecordService;

import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/dashboard")
@SessionAttributes({ "admin", "student", "medicalHistories", "examinations", "isStudentExist" })
public class AdminDashboard {

    private final IAdminService adminService;
    private final IRecordService recordService;

    public AdminDashboard(IAdminService adminService, IRecordService recordService) {
        this.adminService = adminService;
        this.recordService = recordService;
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

    @ModelAttribute("medicalHistories")
    public ArrayList<MedicalHistory> medicalHistoryList() {
        return new ArrayList<MedicalHistory>();
    }

    @ModelAttribute("examinations")
    public ArrayList<Examination> examinationList() {
        return new ArrayList<Examination>();
    }

    @GetMapping()
    public String adminDashboard(Model model) {
        return "adminDashboard";
    }

    @PostMapping("/findStudent")
    public String findStudent(@Valid Student student, Errors errors, Model model) {

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
            @Valid Record record,
            @SessionAttribute Student student,
            @SessionAttribute Admin admin,
            Model model,
            Errors errors) {

        record.setStudent(student);
        record.setAdmin(admin);
        for(MedicalHistory history: record.getMedicalHistory()) {
            history.setRecord(record);
        }
        for(Examination examination: record.getExamination()) {
            examination.setRecord(record);
        }

        Record savedRecord = recordService.save(record);
        System.out.println("\n" + savedRecord.toString() + "\n");
        log.info("NEW RECORD: {}", record.toString());
        return "adminDashboard";
    }

    // @PostMapping("/addMedicalHistory")
    // public String addMedicalRecord(
    // @Valid MedicalHistory medicalHistory,
    // @SessionAttribute ArrayList<MedicalHistory> medicalHistories,
    // @SessionAttribute Student student,
    // Model model,
    // Errors errors) {

    // if (errors.hasErrors()) {
    // return "adminDashboard";
    // }

    // medicalHistories.add(medicalHistory);
    // log.info("Student: {}", student.toString());
    // log.info("NEW MEDICAL HISTORY: {}", medicalHistory.toString());
    // log.info("MED HISTORY: {}", medicalHistories.toString());

    // return "adminDashboard";
    // }

    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/admin";
    }

}
