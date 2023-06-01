package com.mabini.meditrack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mabini.meditrack.models.StudentAccount;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long>{

    Optional<StudentAccount> findByEmailAndPassword(String email, String password);

    StudentAccount findByStudent_LRN(long LRN);

}
