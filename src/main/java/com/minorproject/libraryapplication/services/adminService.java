package com.minorproject.libraryapplication.services;

import com.minorproject.libraryapplication.Model.Admin;
import com.minorproject.libraryapplication.Model.applicationUser;
import com.minorproject.libraryapplication.repositories.adminRepository;
import com.minorproject.libraryapplication.repositories.userRepository;
import com.minorproject.libraryapplication.requestPOJO.adminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class adminService{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    userRepository userRepository;

    @Autowired
    adminRepository adminRepository;

    public void registerAdmin(adminRequest adminRequest) throws Exception {

        try {
            applicationUser user = applicationUser.builder()
                    .username(adminRequest.getUserRequest().getUsername())
                    .password(passwordEncoder.encode(adminRequest.getUserRequest().getPassword()))
                    .authorities("ADM")
                    .build();

            user=userRepository.save(user);

            Admin admin = Admin.builder()
                    .name(adminRequest.getName())
                    .email(adminRequest.getEmail())
                    .age(adminRequest.getAge())
                    .user(user)
                    .build();
            adminRepository.save(admin);
        }catch (Exception e){
            throw new Exception(e.getMessage()+" username is already taken !");
        }
    }

    public String adminName(int id){
        return adminRepository.findById(id).orElseThrow().getName();
    }
}
