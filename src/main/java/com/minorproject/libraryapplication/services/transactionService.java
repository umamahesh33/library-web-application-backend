package com.minorproject.libraryapplication.services;

import com.minorproject.libraryapplication.Model.*;
import com.minorproject.libraryapplication.repositories.transactionRepository;
import com.minorproject.libraryapplication.requestPOJO.issueBookRequest;
import com.minorproject.libraryapplication.requestPOJO.returnBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class transactionService{

    @Autowired
    transactionRepository transactionRepository;

    @Autowired
    studentService studentService;

    @Autowired
    bookService bookService;

    @Transactional
    public String issueBook(issueBookRequest issueBookRequest) throws Exception {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        applicationUser user=(applicationUser) authentication.getPrincipal();

        int student_id = user.getStudent().getId();
        int book_id = issueBookRequest.getBook_id();

        Student student = studentService.getStudentById(student_id);
        Book book = bookService.getBookById(book_id);

        if (book == null) {
            throw new Exception("book with id : " + book_id + " is not present in the library");
        }

        if (book.getStudent() != null) {
            throw new Exception("book was already assigned to someone else");
        }

        if (student == null) {
            throw new Exception("student with id : " + student_id + " was not present");
        }

        int booksCount = student.getBooksCount();
        if (booksCount == 3) {
            throw new Exception("A student can not hold more than 3 books");
        }

        Transactions transactions = Transactions.builder()
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUE)
                .TransactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        try {
            transactions = transactionRepository.save(transactions);
            book.setStudent(student);
            bookService.saveOrUpdateBook(book);
            student.setBooksCount(booksCount + 1);
            studentService.updateOrSaveStudent(student);
            transactions.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transactions);
            return ("Transaction Id: " + transactions.getTransactionId());
        } catch (Exception e) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("exception occurred while completing the transaction :" + transactions.getTransactionId());
        }
    }


    public String returnBook(returnBookRequest returnBookRequest) throws Exception {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        applicationUser user=(applicationUser) authentication.getPrincipal();

        int book_id = returnBookRequest.getBook_id();
        int student_id = user.getStudent().getId();

        Transactions transaction = transactionRepository.getIssueTransaction(book_id, student_id);
        if (!TransactionType.ISSUE.equals(transaction.getTransactionType())) {
            throw new Exception("Issue transaction not found");
        }

        Book book = bookService.getBookById(book_id);
        Student student = studentService.getStudentById(student_id);

        if (book == null) {
            throw new Exception("book with id : " + book_id + " is not belongs to the library");
        }

        if (book.getStudent() == null) {
            throw new Exception("book was already returned by the student");
        }

        if (student == null) {
            throw new Exception("student with id : " + student_id + " was not present");
        }
        int booksCount = student.getBooksCount();


        long bookTakenDate = transaction.getTransactionDate().getTime();
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - bookTakenDate;
        long daysPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS);
        int fine = 0;
        if (daysPassed == 15 ) {
            fine = (int) (5 * (daysPassed - 15));
        }

        Transactions returnTransaction = Transactions.builder()
                .book(book)
                .student(student)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionType(TransactionType.RETURN)
                .TransactionId(UUID.randomUUID().toString())
                .remarks(returnBookRequest.getRemarks())
                .fine(fine)
                .build();

        try {
            returnTransaction = transactionRepository.save(returnTransaction);
            book.setStudent(null);
            bookService.saveOrUpdateBook(book);
            student.setBooksCount(booksCount - 1);
            studentService.updateOrSaveStudent(student);
            returnTransaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(returnTransaction);
            return ("Transaction Id: " + returnTransaction.getTransactionId());
        } catch (Exception e) {
            returnTransaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(returnTransaction);
            throw new Exception("transaction failed ! Transaction Id " + returnTransaction.getTransactionId());
        }

    }

    public List<Transactions> getAllTransactionsOfStudent(int student_id){
       return transactionRepository.getAlltransactionsOfStudent(student_id);
    }
}
