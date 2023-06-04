package com.mabini.meditrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.mabini.meditrack.models.Record;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/student-record")
@SessionAttributes({"studentAccount", "record"})
public class StudentRecord {

    @GetMapping()
    public String studentRecord(Model model, Record record) {
        System.out.println("--------------------\n\n");
        System.out.println("record from post: " + record.toString());
        System.out.println("--------------------\n\n");
        System.out.println(model.toString());
        return "student-record";
    }
    
}
