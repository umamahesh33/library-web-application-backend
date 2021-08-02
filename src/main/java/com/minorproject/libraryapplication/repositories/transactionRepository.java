package com.minorproject.libraryapplication.repositories;

import com.minorproject.libraryapplication.Model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface transactionRepository extends JpaRepository<Transactions,Integer>{


    @Query(value = "select * from transactions t where t.book_book_id=?1 and t.student_id=?2 order by t.id desc limit 1",nativeQuery = true)
    Transactions getIssueTransaction(int book_id,int student_id);

    @Query(value = "select * from transactions t where t.student_id=?1",nativeQuery = true)
    List<Transactions> getAlltransactionsOfStudent(int student_id);
}