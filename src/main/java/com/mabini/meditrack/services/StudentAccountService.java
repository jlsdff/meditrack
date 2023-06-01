package com.mabini.meditrack.services;

import com.mabini.meditrack.mappers.StudentAccountMapper;
import com.mabini.meditrack.models.Student;
import com.mabini.meditrack.models.StudentAccount;
import com.mabini.meditrack.repositories.StudentAccountRepository;
import com.mabini.meditrack.repositories.StudentRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class StudentAccountService implements IStudentAccountService {

    private final StudentAccountRepository studentAccountRepository;
    private final StudentRepository studentRepository;

    public StudentAccountService(StudentAccountRepository studentAccountRepository, StudentRepository studentRepository) {
        this.studentAccountRepository = studentAccountRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentAccount login(String email, String password) {

        StudentAccount account = studentAccountRepository.findByEmailAndPassword(email, password).orElse(null);

        return account;

    }

    @Override
    public StudentAccount register(StudentAccountMapper studentAccountMapper) {

        long lrn = studentAccountMapper.getLRN();
        String email = studentAccountMapper.getEmail();
        String password = studentAccountMapper.getPassword();

        Student student = studentRepository.findByLRN(lrn);
        StudentAccount ExistingAccount = studentAccountRepository.findByStudent_LRN(lrn);
        
        if (student == null || ExistingAccount != null) {
            return null;
        }

        StudentAccount account = new StudentAccount();
        account.setEmail(email);
        account.setPassword(password);
        account.setStudent(student);

        return studentAccountRepository.save(account);

    }
}
