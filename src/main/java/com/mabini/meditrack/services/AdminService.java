package com.mabini.meditrack.services;

import org.springframework.stereotype.Service;

import com.mabini.meditrack.models.Admin;
import com.mabini.meditrack.repositories.AdminRepository;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
    
    @Override
    public Admin login(String email, String password) {

        Admin admin = adminRepository.findByEmailAndPassword(email, password);

        if (admin != null) {
            return admin;
        }

        return null;

    }


    
}
