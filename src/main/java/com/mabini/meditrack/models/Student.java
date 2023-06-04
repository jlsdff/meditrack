package com.mabini.meditrack.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    private long LRN;

    @Column(name = "first_name")
    private String firstName;  

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    private int age;

    @Column(name="birth_date")
    private Date birthDate;
    
    @Column(name="contact_number")
    private long contactNumber; 

    @OneToOne(cascade = {CascadeType.ALL})
    private EmergencyRecord emergencyContact;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "student")
    private List<Record> record;
}
