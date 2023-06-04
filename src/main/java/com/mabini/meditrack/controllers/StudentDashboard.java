package com.mabini.meditrack.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mabini.meditrack.models.Student;
import com.mabini.meditrack.models.StudentAccount;
import com.mabini.meditrack.services.IRecordService;
import com.mabini.meditrack.models.Record;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dashboard")
@SessionAttributes({"studentAccount", "record"})
public class StudentDashboard {

    private final IRecordService recordService;

    public StudentDashboard(IRecordService recordService) {
        this.recordService = recordService;
    }

    @ModelAttribute("record")
    public Record record() {
        return new Record();
    }


    @GetMapping
    public String dashboard(Model model) {

        Student student = ((StudentAccount) model.getAttribute("studentAccount")).getStudent();
        
        List<Record> records = recordService.findAllByStudent_LRN(student.getLRN());
        model.addAllAttributes(records);
        System.out.println("--------------------\n\n");
        System.out.println("records from get: " + records.toString());

        return "dashboard";
    }

    @PostMapping()
    public String studentRecord(Model model, Record record) {
        System.out.println("record from post: " + record.toString());
        return "student-record";
    }
    
}
