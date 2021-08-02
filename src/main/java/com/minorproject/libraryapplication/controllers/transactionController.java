package com.minorproject.libraryapplication.controllers;


import com.minorproject.libraryapplication.Model.Book;
import com.minorproject.libraryapplication.Model.Transactions;
import com.minorproject.libraryapplication.Model.applicationUser;
import com.minorproject.libraryapplication.requestPOJO.issueBookRequest;
import com.minorproject.libraryapplication.requestPOJO.returnBookRequest;
import com.minorproject.libraryapplication.services.transactionService;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class transactionController{

    @Autowired
    transactionService transactionService;

    @PostMapping("/issue")
    public String issueBook(@RequestBody issueBookRequest issueBookRequest) throws Exception {
            return transactionService.issueBook(issueBookRequest);
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody returnBookRequest returnBookRequest) throws Exception {
            return transactionService.returnBook(returnBookRequest);
    }

    @GetMapping("/all")
    public List<Transactions> getAllTransactionsOfStudent(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        applicationUser user=(applicationUser) authentication.getPrincipal();
        return transactionService.getAllTransactionsOfStudent(user.getStudent().getId());
    }
}
