package com.mabini.meditrack.models;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "record_id")
    private Record record;
    
    // @Override
    // public String toString(){
    //     return "MedicalHistory{" +
    //             "medicalHistoryId=" + medicalHistoryId +
    //             ", name='" + name + '\'' +
    //             ", detail='" + detail + '\'' +
    //             '}';
    // }
}
