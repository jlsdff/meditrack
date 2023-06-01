package com.mabini.meditrack.services;

import com.mabini.meditrack.mappers.StudentAccountMapper;
import com.mabini.meditrack.models.StudentAccount;
import org.springframework.stereotype.Service;


@Service
public interface IStudentAccountService {

    public StudentAccount login(String email, String password);

    public StudentAccount register(StudentAccountMapper studentAccountMapper);

}
