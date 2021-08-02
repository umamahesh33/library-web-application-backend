package com.minorproject.libraryapplication.repositories;

import com.minorproject.libraryapplication.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface authorRepository extends JpaRepository<Author,Integer>{

    Author findByEmail(String email);
}
