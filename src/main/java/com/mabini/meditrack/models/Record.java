package com.mabini.meditrack.models;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "records")
public class Record implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recordId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "record", fetch = FetchType.EAGER)
    private List<MedicalHistory> medicalHistory;    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "record", fetch = FetchType.EAGER)
    private List<Examination> examination;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Admin admin;

    public Record(List<MedicalHistory> medicalHistory,List<Examination> examination, Student student,Admin admin ) {
        this.medicalHistory = medicalHistory;
        this.examination = examination;
        this.student = student;
        this.admin = admin;
    }

    public Record() {
    }


    public void addMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory.add(medicalHistory);
    }
    
    public void addExamination(Examination examination) {
        this.examination.add(examination);
    }

}
