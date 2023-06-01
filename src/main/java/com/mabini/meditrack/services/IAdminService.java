package com.mabini.meditrack.services;

import org.springframework.stereotype.Service;

import com.mabini.meditrack.models.Admin;

@Service
public interface IAdminService {

    Admin login(String email, String password);
    
}
