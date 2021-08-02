package com.minorproject.libraryapplication.repositories;

import com.minorproject.libraryapplication.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepository extends JpaRepository<Admin,Integer>{
}
