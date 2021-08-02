package com.minorproject.libraryapplication.controllers;

import com.minorproject.libraryapplication.Model.Author;
import com.minorproject.libraryapplication.services.authorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authorController{

    @Autowired
    authorService authorService;

    @GetMapping("/author")
    public Author getAuthorByEmail(@RequestParam(name = "email")String email){

        return authorService.getAuthorByEmail(email);
    }

    @GetMapping("/author/id")
    public Author getAuthorById(@RequestParam(name = "id")int id){
        return authorService.getAuthorById(id);
    }

}
