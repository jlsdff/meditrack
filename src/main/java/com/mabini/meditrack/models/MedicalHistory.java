package com.mabini.meditrack.models;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "medical_histories")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long medicalHistoryId;

    @NotBlank(message = "Detail is required")
    private String name;
    
    @NotBlank(message = "Detail is required")
    private String detail;

    // @ManyToOne(cascade = {CascadeType.ALL})
    // private Record record;
    
}
