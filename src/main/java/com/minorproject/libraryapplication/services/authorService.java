package com.minorproject.libraryapplication.services;

import com.minorproject.libraryapplication.Model.Author;
import com.minorproject.libraryapplication.repositories.authorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class authorService{

    @Autowired
    authorRepository authorRepository;

    public void createAuthor(Author author){
        authorRepository.save(author);
    }

    public Author getAuthorByEmail(String email){
        return authorRepository.findByEmail(email);
    }

    public Author getAuthorById(int id){
        return authorRepository.findById(id).orElseThrow();
    }
}
