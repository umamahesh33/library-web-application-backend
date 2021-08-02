package com.minorproject.libraryapplication.repositories;

import com.minorproject.libraryapplication.Model.applicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<applicationUser,Integer>{

    public UserDetails findByUsername(String username);
}
