package com.minorproject.libraryapplication.controllers;


import com.minorproject.libraryapplication.Model.Author;
import com.minorproject.libraryapplication.Model.Book;
import com.minorproject.libraryapplication.requestPOJO.bookRequest;

import com.minorproject.libraryapplication.services.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class bookController{

    @Autowired
    bookService bookService;

    @GetMapping("/book/all")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/book/id")
    public Book getBookById(@RequestParam(name = "id")int book_id){
        return bookService.getBookById(book_id);
    }

    @GetMapping("book/name")
    public List<Book> getBookByName(@RequestParam(name = "name")String name){
        return bookService.getBookByName(name);
    }

    @RequestMapping(path = "/book",method = RequestMethod.POST)
    public void createBook(@RequestBody bookRequest bookRequest){
        bookService.createBook(bookRequest);
    }

    @RequestMapping(path = "/book/id",method = RequestMethod.POST)
    public void updateBookById(@RequestParam(name = "id") int book_id,@RequestBody bookRequest bookRequest){
        bookService.updateBookById(book_id,bookRequest);
    }

    @DeleteMapping("book/delete/{id}")
    public String deleteBookById(@PathVariable(name = "id") int id){
        return bookService.deleteBookById(id);
    }



}