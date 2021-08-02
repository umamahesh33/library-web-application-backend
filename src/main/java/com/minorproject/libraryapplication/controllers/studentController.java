package com.minorproject.libraryapplication.controllers;


import com.minorproject.libraryapplication.Model.Book;
import com.minorproject.libraryapplication.Model.Student;
import com.minorproject.libraryapplication.Model.applicationUser;
import com.minorproject.libraryapplication.requestPOJO.studentRequest;
import com.minorproject.libraryapplication.services.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class studentController{

    @Autowired
    studentService studentService;

    @GetMapping("/student")
    public String getStudentById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        applicationUser user=(applicationUser) authentication.getPrincipal();
        return "hello "+studentService.getStudentById(user.getStudent().getId()).getName();
    }

    @GetMapping("/student/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/student/books")
    public List<String> getBooksTakenByStudent(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        applicationUser user=(applicationUser) authentication.getPrincipal();
        List<Book> resultList=studentService.getBooksTakenByStudent(user.getStudent().getId());
        return resultList.stream().map(book->book.getBookName()).collect(Collectors.toList());
    }

    @PostMapping("/student")
    public void createStudent(@RequestBody studentRequest studentRequest) throws Exception {
        studentService.createStudent(studentRequest);
    }

    @PostMapping("/student/update")
    public void updateStudentById(@RequestBody studentRequest studentRequest){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        applicationUser user=(applicationUser) authentication.getPrincipal();

        studentService.updateStudentById(user.getStudent().getId(),studentRequest);
    }

    @DeleteMapping("/student/delete")
    public String deleteStudent(@RequestParam(name = "id") int id){
        return studentService.deleteStudent(id);
    }

}
