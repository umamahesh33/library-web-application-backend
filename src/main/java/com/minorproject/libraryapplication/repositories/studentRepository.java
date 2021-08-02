package com.minorproject.libraryapplication.repositories;

import com.minorproject.libraryapplication.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<Student,Integer>{
}
