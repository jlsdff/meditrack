package com.mabini.meditrack.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mabini.meditrack.models.Admin;
import com.mabini.meditrack.models.Student;
import com.mabini.meditrack.repositories.AdminRepository;
import com.mabini.meditrack.repositories.StudentRepository;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;

    public AdminService(AdminRepository adminRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Admin login(String email, String password) {

        Admin admin = adminRepository.findByEmailAndPassword(email, password);

        if (admin != null) {
            return admin;
        }

        return null;

    }

    @Override
    public Student findStudent(long LRN) {

        Optional<Student> foundStudent = Optional.ofNullable(studentRepository.findByLRN(LRN));
        
        return foundStudent.orElse(null);

    }

}
