package com.minorproject.libraryapplication.controllers;

import com.minorproject.libraryapplication.Model.applicationUser;
import com.minorproject.libraryapplication.requestPOJO.adminRequest;
import com.minorproject.libraryapplication.services.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class applicationController{

    @Autowired
    adminService adminService;

    @GetMapping("/")
    public String homePage(){
        return "hello user!";
    }

    @PostMapping("/admin/register")
    public void createAdmin(@RequestBody adminRequest adminRequest) throws Exception {
        adminService.registerAdmin(adminRequest);
    }

    @GetMapping("/admin")
    public String getAdminName(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        applicationUser user= (applicationUser) authentication.getPrincipal();
        return adminService.adminName(user.getAdmin().getId());
    }
}
