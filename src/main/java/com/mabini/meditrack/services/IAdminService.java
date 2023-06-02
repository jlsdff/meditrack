package com.mabini.meditrack.services;

import org.springframework.stereotype.Service;

import com.mabini.meditrack.models.Admin;
import com.mabini.meditrack.models.Student;

@Service
public interface IAdminService {

    Admin login(String email, String password);

    Student findStudent(long LRN);

    
}
